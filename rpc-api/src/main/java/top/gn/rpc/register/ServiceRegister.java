package top.gn.rpc.register;

/**
 * 前一节实现的demo是手动注册一个服务，服务器启动的时候手动注册进一个指定的服务
 * 所以改进后为服务的注册和服务器启动分离，使得服务端提供多个服务
 */

/**
 * 以下为服务注册表实现
 *      首先实现一个容器，保存一个提供的服务的信息，并且在获得一个服务名字的时候
 *          能够返回这个服务的信息
 */
public interface ServiceRegister {
    //register注册信息
    <T> void register(T service);
    //getService获取信息
    Object getService(String serviceName);
}
