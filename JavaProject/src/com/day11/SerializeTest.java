package com.day11;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

//p551 직렬화
class Person implements Serializable{
	private String name;
	private String job;
	public Person(String name, String job) {
		this.name = name;
		this.job =job;
	}
	@Override
	public String toString() {
		return "name=" + name + ", job=" + job ;
	}
}
public class SerializeTest {
	public static void main(String[] args) {
		 Person personAhn = new Person("안재용","대표이사");
		 Person personKim = new Person("김철수","상무이사");
		 try(FileOutputStream fos = new FileOutputStream("serial.out");
				 ObjectOutputStream oos = new ObjectOutputStream(fos)){
			 oos.writeObject(personAhn);
			 oos.writeObject(personKim);
			 
		 } catch (FileNotFoundException e) {
					e.printStackTrace();
		} catch (IOException e) {
					e.printStackTrace();
		}
		 
		 //////
		 try(FileInputStream fis = new FileInputStream("serial.out");
				 ObjectInputStream ois = new ObjectInputStream(fis) ){
			 Person p1 = (Person) ois.readObject();
			 Person p2 =(Person)ois.readObject();
			 System.out.println(p1);
			 System.out.println(p2);
		 } catch (FileNotFoundException e) {
				e.printStackTrace();
		} catch (IOException e) {
					e.printStackTrace();
		} catch (ClassNotFoundException e) {
				e.printStackTrace();
		}


	}

}







