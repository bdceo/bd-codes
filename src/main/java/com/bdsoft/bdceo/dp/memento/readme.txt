参考：https://blog.csdn.net/q547550831/article/details/70155284
    https://blog.csdn.net/qq_16687803/article/details/46661849

备忘录模式提供的基本功能是：保存对象状态信息(快照)、撤销、重做和历史记录。
备忘录模式一般会提供两种接口：宽接口和窄接口。
    通过宽接口可以获取整个对象状态，会暴露备忘录对象的内部信息。
    通过窄接口，只能访问有限的，开发者限制了的信息，可以有效的防止信息泄露。

备忘录模式：在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态。这样以后就可以将该对象恢复到原先保存的状态。
适用场合：比较适合用于功能比较复杂，但需要维护或记录属性历史的类，或者需要保存的属性只是众多属性中的一小部分时。

备忘录模式中的角色
Originator(生成者)
Originator角色会在保存自己的最新状态时生成Memento角色。当把以前保存的Memento角色传递给Originator角色时，它会将自己恢复至生成该Memento角色时的状态。在案例中，由Gamer类扮演此角色。

Memento(纪念品)
Memento角色会将Originator角色的内部信息整合在一起。在Memento角色中虽然保存了Originator角色的信息，但它不会向外部公开这些信息。
Memento角色有以下两种接口（API）。

wide interface——宽接口（API）
Memento角色提供的“宽接口（API）”是指所有用于获取恢复对象状态信息的方法的集合。由于宽接口（API）会暴露所有Memento角色的内部信息，因此能够使用宽接口（API）的只有Originator角色。
narrow interface——窄接口（API）
Memento角色为外部的Caretaker角色提供了“窄接口（API）” 。可以通过窄接口（API）获取的Memento角色的内部信息、非常有限，因此可以有效地防止信息泄露。
通过对外提供以上两种接口（API），可以有效地防止对象的封装性被破坏。
在案例中，由Memento类扮演此角色。
Originator角色和Memento角色之间有着非常紧密的联系。

Caretaker(负责人)
当Caretaker角色想要保存当前的Originator角色的状态时，会通知Originator角色。Originator角色在接收到通知后会生成Memento角色的实例并将其返回给Caretaker角色。由于以后可能会用Memento实例来将Originator 恢复至原来的状态，因此Caretaker角色会一直保存Memento实例。在案例中，由测试类扮演此角色。
不过，Caretaker角色只能使用Memento角色两种接口（API）中的窄接口（API），也就是说它无法访问Memento 角色内部的所有信息。它只是将Originator角色生成的Memento角色当作一个黑盒子保存起来。
虽然Originator角色和Memento角色之间是强关联关系，但Caretaker角色和Memento角色之间是弱关联关系。Memento角色对Caretaker角色隐藏了自身的内部信息。


