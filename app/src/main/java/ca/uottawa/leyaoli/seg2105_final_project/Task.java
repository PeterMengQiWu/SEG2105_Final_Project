package ca.uottawa.leyaoli.seg2105_final_project;

import android.support.annotation.NonNull;

/**
 * Created by Kevin-Lee on 2017/11/27.
 */

class Task implements Comparable<Task>{
    private String name;
    private double points;
    private String dueDate;
    private String dueTime;
    private String creator;
    private String worker;
    private String states;

    public Task(){}

    public Task(String name, double points, String dueDate, String dueTime, String creator, String states){
        this. name = name;
        this.points = points;
        this.dueDate = dueDate;
        this.dueTime = dueTime;
        this.creator = creator;
        this.states = states;
    }

    public Task(String name, double points, String dueDate, String dueTime, String creator, String worker, String states){
        this. name = name;
        this.points = points;
        this.dueDate = dueDate;
        this.dueTime = dueTime;
        this.creator = creator;
        this.worker = worker;
        this.states = states;
    }

    public String getName(){return name;}
    public double getPoints(){return points;}
    public String getDueDate(){return  dueDate;}
    public String getDueTime(){return dueTime;}
    public String getCreator(){return creator;}
    public String getWorker(){return  worker;}
    public String getStates(){return  states;}

    public void setName(String name){this.name = name;}
    public void setPoints(double points){this.points = points;}
    public void setDueDate(String dueDate){this.dueDate = dueDate;}
    public void setDueTime(String dueTime){this.dueTime = dueTime;}
    public void setCreator(String creator){this.creator = creator;}
    public void setWorker(String worker){this.worker = worker;}
    public void setStates(String states){this.states = states;}

    @Override
    public int compareTo(@NonNull Task o) {
        return (this.getDueDate()+" "+this.getDueTime()).compareTo(o.getDueDate()+" "+o.getDueTime());
    }
}
