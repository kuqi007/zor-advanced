package com.zor.basic.concept;

public class ShallowCopyTest {
    public static void main(String[] args) {
        ShallowCopyTest a = new ShallowCopyTest();
        Name name = a.new Name("张", "三");
        User user1 = a.new User();//被克隆对象
        User user2 = a.new User();//克隆后对象
        user1.setAge(12);
        user1.setId(1);
        user1.setName(name);
        try {
            user2 = user1.clone();
            user2.setId(2);
            user2.getName().setLastName("四");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("user1:" + user1.toString());
        System.out.println("user2:" + user2.toString());

    }

    //克隆对象
    public class User implements Cloneable {
        public int id;
        public int age;
        public Name name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Name getName() {
            return name;
        }

        public void setName(Name name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "id:" + id + ",age:" + age + ",address:" + name;
        }

        @Override
        public User clone() throws CloneNotSupportedException {
            User u = (User) super.clone();
            return u;
        }

    }

    //引用对象
    public class Name {
        private String firstName;
        private String lastName;

        public Name(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        @Override
        public String toString() {
            return "firstName:" + firstName + ",lastName:" + lastName;
        }

    }
}
