参考：https://www.cnblogs.com/zyrblog/p/9250726.html

    https://blog.csdn.net/wujunyucg/article/details/78904817

    https://my.oschina.net/lgmcolin/blog/84098

    http://www.blogjava.net/pengpenglin/archive/2008/03/05/183973.html

FLYWEIGHT在拳击比赛中指最轻量级。
享元模式以共享的方式高效的支持大量的细粒度对象。
享元模式能做到共享的关键是区分内蕴状态和外蕴状态。
    内蕴状态存储在享元内部，不会随环境的改变而有所不同。
    外蕴状态是随环境的改变而改变的。外蕴状态不能影响内蕴状态，它们是相互独立的。
将可以共享的状态和不可以共享的状态从常规类中区分开来，将不可以共享的状态从类里剔除出去。
客户端不可以直接创建被共享的对象，而应当使用一个工厂对象负责创建被共享的对象。

享元模式大幅度的降低内存中对象的数量。