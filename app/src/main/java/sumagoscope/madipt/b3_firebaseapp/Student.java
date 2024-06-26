package sumagoscope.madipt.b3_firebaseapp;

public class Student {
    int age;
    String name, docId;

    public Student(String docId, String name, int age) {
        this.age = age;
        this.name = name;
        this.docId = docId;
    }

    public Student() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
