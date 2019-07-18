public class products
{
    private int id;
    private String name;
    private String descriptian;
    private String salary;
    private String salaryprice;
    private String discount;
    private  String thumbnail;
    private String image1;
    private String image2;
    private String departmentname;
    private int departmentid;

    public products() {
    }

    public products(int id, String name, String descriptian, String salary, String salaryprice, String discount, String thumbnail, String image1, String image2, String  departmentname, int departmentid) {
    this.id=id;this.name=name;this.descriptian=descriptian;this.salary=salary;this.salaryprice=salaryprice;this.discount=discount;this.thumbnail=thumbnail;this.image1=image1;this.image2=image2;this.departmentname=departmentname;this.departmentid=departmentid;

    }

    public int getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(int departmentid) {
        this.departmentid = departmentid;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getSalaryprice() {
        return salaryprice;
    }

    public void setSalaryprice(String salaryprice) {
        this.salaryprice = salaryprice;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getDescriptian() {
        return descriptian;
    }

    public void setDescriptian(String descriptian) {
        this.descriptian = descriptian;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
