参考：https://blog.csdn.net/wujunyucg/article/details/78910615
    https://www.iteye.com/blog/men4661273-1633775
    http://blog.chinaunix.net/uid-29140694-id-4134135.html
    http://www.ayqy.net/blog/%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F%E4%B9%8B%E5%91%BD%E4%BB%A4%E6%A8%A1%E5%BC%8F%EF%BC%88command-pattern%EF%BC%89/

简述
Command 模式是使用表示命令的类来代替方法的调用，这样就可以管理工作的历史记录。

角色和 UML
Command
定义命令的接口（API）。

ConcreteCommand
负责实现在 Command 角色中定义的接口（API）。

Receiver
是 Command 角色执行命令时的对象，可以称为命令接收者。

Client
负责生成 ConcreteCommand 角色并分配 Receiver 角色。

Invoker
是开始执行命令的角色，会调用 Command 角色中定义的接口（API）。