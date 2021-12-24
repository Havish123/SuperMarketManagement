package Modal;

import DAO.RepresentiveDAO;

import java.util.ArrayList;
import java.util.List;

public class Representative {
    private List<Representative> repList=new ArrayList<Representative>();
    private int rep_id;
    private String rep_name;
    private int age;
    private int salary;
    private String rep_phoneno;
    private String rep_email;
    private String rep_type;
    private String passcode;
    public Representative(){

    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setRep_type(String rep_type) {
        this.rep_type = rep_type;
    }

    public String getRep_type() {
        return rep_type;
    }

    public Representative(int rep_id, String rep_name, int age, int salary, String rep_phoneno, String rep_email, String rep_type,String passcode){
        this.rep_id=rep_id;
        this.rep_name=rep_name;
        this.age=age;
        this.salary=salary;
        this.rep_phoneno=rep_phoneno;
        this.rep_email=rep_email;
        this.rep_type=rep_type;
        this.passcode=passcode;
    }

    public String getRep_email() {
        return rep_email;
    }

    public String getRep_phoneno() {
        return rep_phoneno;
    }

    public void setRep_phoneno(String rep_phoneno) {
        this.rep_phoneno = rep_phoneno;
    }

    public void setRep_email(String rep_email) {
        this.rep_email = rep_email;
    }

    public List<Representative> addRep(Representative rep){
        repList.add(rep);
        return repList;
    }
    public List<Representative> getRepList() {
        RepresentiveDAO repDAO=new RepresentiveDAO();
        repDAO.connect();
        setRepList(repDAO.retriveData());
        repDAO.conClose();
        return repList;
    }

    public void setRepList(List<Representative> repList) {
        this.repList = repList;
    }

    public int getAge() {
        return age;
    }

    public int getRep_id() {
        return rep_id;
    }

    public int getSalary() {
        return salary;
    }

    public String getRep_name() {
        return rep_name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setRep_id(int rep_id) {
        this.rep_id = rep_id;
    }

    public void setRep_name(String rep_name) {
        this.rep_name = rep_name;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

}
