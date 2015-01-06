package com.exampreparations.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Test {

	public static void main(String[] args) {
		Random rand = new Random();
		/*for(int i=0;i<6;i++){
		int x = rand.nextInt(10);
		System.out.println("###########################"+x);
		}*/
		
		List<Integer> s=new ArrayList<Integer>();
		 
		for (int i = 0; i < 10; i++) {
	        s.add(i + 1);
	    }
		
		System.out.println("########################"+s);
		
		Collections.shuffle(s, rand);
		
		System.out.println("#####@@@@@@@@@@@@@@@@@@@@####################"+s);
		
		Iterator it=s.iterator();
		
			for(int j=0;j<5;j++){
			System.out.println("###########################"+it.next());
			}
	}

}
