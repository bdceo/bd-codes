package com.bdsoft.bdceo.dp.factory.nvwa;

// 女娲造人、动物
public interface NvwaFactory {

    String BASE = "com.bdsoft.bdceo.dp.factory.nvwa.";

    Person genPerson(String type);

    Animal genAnimal(String type);
}

// 阳性-男人
class YangSheng implements NvwaFactory {

    @Override
    public Person genPerson(String type) {
        System.out.println("YangSheng-genPerson" + type);
        return new Man();
    }

    @Override
    public Animal genAnimal(String type) {
        System.out.println("YangSheng-genAnimal" + type);
        return new Bull();
    }

}

// 阴性-女人
class YinSheng implements NvwaFactory {

    @Override
    public Person genPerson(String type) {
        System.out.println("YinSheng-genPerson" + type);
        return new Woman();
    }

    @Override
    public Animal genAnimal(String type) {
        System.out.println("YinSheng-genAnimal" + type);
        return new Cow();
    }
}