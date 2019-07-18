package e.inspiron.e_commercechallenge;

public class buyitems {
    private  String startdate,finishdate,feedback,feedbackdate;
  public  buyitems(String id,String ownername,String userid,String username,String itemname,String itemid,String card){this.id=id;this.ownername=ownername;this.userid=userid;this.usename=username;this.itemname=itemname;this.itemid=itemid; this.card=card;
  }
    public  buyitems(String id,String ownername,String userid,String username,String itemname,String itemid,String card,String startdate,String finishdate,String feedback,String feedbackdate){this.id=id;this.ownername=ownername;this.userid=userid;this.usename=username;this.itemname=itemname;this.itemid=itemid; this.card=card;this.finishdate=finishdate;this.startdate=startdate;this.feedback=feedback;this.feedbackdate=feedbackdate;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getFinishdate() {
        return finishdate;
    }

    public void setFinishdate(String finishdate) {
        this.finishdate = finishdate;
    }

    public String getFeedbackdate() {
        return feedbackdate;
    }

    public void setFeedbackdate(String feedbackdate) {
        this.feedbackdate = feedbackdate;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public  buyitems(String id, String ownername, String userid, String username, String itemname, String itemid, String card, String startdate, String finishdate){this.id=id;this.ownername=ownername;this.userid=userid;this.usename=username;this.itemname=itemname;this.itemid=itemid; this.card=card;this.startdate=startdate;this.finishdate=finishdate;
    }
   private String id,ownername,userid,usename,itemname,itemid,card;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsename() {
        return usename;
    }

    public void setUsename(String usename) {
        this.usename = usename;
    }

    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }
}
