package utils;

public class RemoteNetUtilFactory extends AbstractRemoteNetUtilFactory{

    @Override
    public RemoteNetUtil createRemoteNetUtil() {
        return new DefaultRemoteNetUtil();
    }
}
