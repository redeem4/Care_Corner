package com.carecorner;

public class Reporting {
    private String name;

    public Reporting(String name) {
        this.name=name;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name=name;}

   // @Override
  //  public boolean equals(Object obj)
   // {
   //     if (this == obj)
   //         return true;

   //     if (obj == null || this.getClass() != obj.getClass())
   //         return false;

   //     Reporting j1 = (Reporting) obj;

   //     return this.name.equals(j1.name) && this.text.equals(j1.text);
  //  }

    @Override
    public String toString() {
        return "Title:" + " " + name + "/n";
    }

}
