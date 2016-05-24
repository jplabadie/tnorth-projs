#!/usr/bin/env python

from __future__ import print_function
from __future__ import division
"""calculates the SNP density
across an interval"""


import optparse
import sys
from collections import Counter

def test_file(option, opt_str, value, parser):
    try:
        with open(value): setattr(parser.values, option.dest, value)
    except IOError:
        print('%s file cannot be opened' % option)
        sys.exit()

def get_index(matrix):
    with open(matrix, "U") as in_matrix:
        positions=[]
        firstLine = in_matrix.readline()
        first_fields = firstLine.split("\t")
        coords=first_fields.index("Position")
        for line in in_matrix:
            fields = line.split("\t")
            positions.append(fields[coords])
        largest=positions[-1]
    return largest


def parse_matrix(matrix, step, largest):
    start=1
    in_matrix = open(matrix, "U")
    outfile = open("snp_density_ranges.txt", "w")
    firstLine = in_matrix.readline()
    first_fields = firstLine.split("\t")
    coords=first_fields.index("Position")
    last=first_fields.index("#SNPcall")
    in_matrix.close()

    """
        Xrange was originally used in this for loop, which in Python2 gives a significant performance improvement.
        However, xrange is not a function in Python3 (where range always uses the behavior of xrange).
        IF WORKING IN A PYTHON v2 ENVIRONMENT: revert this function to 'xrange' instead of 'range'
    """
    for i in range(int(start), int(largest), int(step)):
        positives=[ ]
        my_range=range(i,i+step)
        with open(matrix) as f:
            next(f)
            for line in f:
                fields = line.split("\t")
                if int(fields[coords]) in my_range:
                    temp = fields[1:last]
                    counter = Counter(temp)
                    values = counter.values()
                    new_values = list(sorted(values, key=int))
                    if len(new_values) == 1:
                        sys.exc_clear()
                    else:
                        for z in range(2,5):
                            if len(new_values)==int(z) and new_values[z-2]>1: positives.append("1")
        print(i, i+step, len(positives), file=outfile )
    outfile.close()

def main(matrix, step):
    largest=get_index(matrix)
    parse_matrix(matrix, step, largest)

if __name__ == "__main__":
    usage="usage: %prog [options]"
    parser = optparse.OptionParser(usage=usage)
    parser.add_option("-m", "--matrix", dest="matrix",
                      help="/path/to/NASP formatted SNP matrix [REQUIRED]",
                      type="string", action="callback", callback=test_file)
    parser.add_option("-s", "--step", dest="step", action="store",
                      help="size for stepping through the genome, defaults to 1000",
                      type="int", default="1000")
    options, args = parser.parse_args()

    mandatories = ["matrix"]
    for m in mandatories:
        if not getattr(options, m, None):
            print ("\nMust provide %s.\n" % m)
            parser.print_help()
            exit(-1)

    main(options.matrix,options.step)
