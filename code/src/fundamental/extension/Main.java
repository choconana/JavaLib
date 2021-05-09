package fundamental.extension;

/**
 * @Description:
 * @author: hezhidong
 */
public class Main {
    public static void main(String[] args) {
        Person person = new Lihua();
        System.out.println(person.property);
        System.out.println(person.num);
        person.say();
        person.eat();
        System.out.println(new Lihua().num);
    }
}
