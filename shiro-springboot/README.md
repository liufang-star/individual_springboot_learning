项目地址：localhost:5656   账号:liufang   密码:123456
Shiro详解：https://blog.csdn.net/private_name/article/details/121189370?spm=1001.2014.3001.5502

Shiro简介
1.Authentication:身份认证/登录，验证用户是不是拥有相应的身份
2.Authorization:授权，即权限验证，验证某个已认证的用户是否拥有某个权限；即判断用户是否能进行什么操作，如：验证某个用户是否拥有某个角色。或者细粒度的验证某个用户对某个资源是否具有某个权限
3.Session Management:会话管理，即用户登录后就是一次会话，在没有退出之前，它的所有信息都在会话中；会话可以是普通JavaSE环境，也可以是Web 环境的
4.Cryptography:加密，保护数据的安全性，如密码加密存储到数据库，而不是明文存储
5.Web Support:Web 支持，可以非常容易的集成到Web 环境
6.Caching:缓存，比如用户登录后，其用户信息、拥有的角色/权限不必每次去查，这样可以提高效率
7.Concurrency:Shiro支持多线程应用的并发验证，即如在一个线程中开启另一个线程，能把权限自动传播过去
8.Testing:提供测试支持
9.“Run As”:允许一个用户假装为另一个用户（如果他们允许）的身份进行访问


