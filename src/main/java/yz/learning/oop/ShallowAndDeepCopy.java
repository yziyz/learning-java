package yz.learning.oop;

import java.util.Date;

/**
 * 浅复制与深复制
 */
public class ShallowAndDeepCopy {
    public static void main(String[] args) throws CloneNotSupportedException{
        /*
        浅复制的比较
         */
        Clock clock01 = new Clock(1, new Date());
        Clock clock02 = (Clock) clock01.clone();
        /*
        浅复制中，如果数据域是对象类型，那么复制的是对象的引用；
        下面输出为true
         */
        System.out.println(clock01.getDateOfProdution() == clock02.getDateOfProdution());

        /*
        深复制比较
         */
        NoteBook noteBook01 = new NoteBook(1, new Date());
        NoteBook noteBook02 = (NoteBook)noteBook01.clone();
        /*
        深复制中复制了对象；
        下面输出为false
         */
        System.out.println(noteBook01.getDateOfProdution() == noteBook02.getDateOfProdution());
    }
}

/**
 * 浅复制
 */
class Clock implements Cloneable {
    private Integer id;
    private Date dateOfProdution;

    Clock(Integer id, Date dateOfProdution) {
        this.id = id;
        this.dateOfProdution = dateOfProdution;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateOfProdution() {
        return dateOfProdution;
    }

    public void setDateOfProdution(Date dateOfProdution) {
        this.dateOfProdution = dateOfProdution;
    }

    @Override
    public String toString() {
        return "Clock{" +
                "id=" + id +
                ", dateOfProdution=" + dateOfProdution +
                '}';
    }
}

/**
 * 深复制
 */
class NoteBook implements Cloneable {
    /**
     * 编号
     */
    private Integer id;
    /**
     * 生产日期
     */
    private Date dateOfProdution;

    NoteBook(Integer id, Date dateOfProdution) {
        this.id = id;
        this.dateOfProdution = dateOfProdution;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        NoteBook noteBook = (NoteBook) super.clone();
        noteBook.dateOfProdution = (Date) (dateOfProdution.clone());
        return noteBook;
    }

    public Date getDateOfProdution() {
        return dateOfProdution;
    }

    public void setDateOfProdution(Date dateOfProdution) {
        this.dateOfProdution = dateOfProdution;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "NoteBook{" +
                "dateOfProdution=" + dateOfProdution +
                ", id=" + id +
                '}';
    }
}