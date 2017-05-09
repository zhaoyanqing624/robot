package org.xjtusicd3.portal.controller;

import java.util.UUID;
/**
 * Create GUID
 * @author Administrator
 *
 */
public class CreateUUID {

 public static final String GenerateGUID(){
  UUID uuid = UUID.randomUUID();
  return uuid.toString();  
 }
 /**
  * @param args
  */
 public static void main(String[] args) {
  // TODO Auto-generated method stub
  System.out.println(GenerateGUID());
 }
}
