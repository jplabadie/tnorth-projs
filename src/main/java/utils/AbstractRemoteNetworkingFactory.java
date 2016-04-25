package utils;

/**
 * @author Jean-Paul Labadie
 * @date 8/11/2015
 */
public interface AbstractRemoteNetworkingFactory {
    public DefaultRemoteNetworkingWrapper createJschWrapper();
}
