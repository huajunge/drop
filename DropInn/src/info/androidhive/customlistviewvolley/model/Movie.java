package info.androidhive.customlistviewvolley.model;

import java.util.ArrayList;

public class Movie {
	private String getid,getaddress,getsymbol,getprice,title, thumbnailUrl,thumbnailUrl1,getuserid,status,checkin,checkout,created,userby,total,check,step,roomtype,city,nguest,symbol1,topay,tripstatus,resid,userto,isread,id1,profilepic,guest,message,username;


	public Movie() {
		
	}

	public Movie(String getuserid,String getid,String getaddress,String getsymbol,String getprice,String name, String thumbnailUrl,String thumbnailUrl1,ArrayList<String> genre,String status,String checkin,String checkout,String created,String userby,String total,String check,String step,String roomtype,String city,String nguest,String symbol1,String topay,String tripstatus,String resid,String userto,String isread,String id1,String profilepic,String guest,String message,String username) {
		
		this.checkin=checkin;
		this.status=status;
		this.checkout=checkout;
		this.getuserid=getuserid;
		this.getid=getid;		
		this.title = name;
		this.getaddress = getaddress;
		this.getsymbol = getsymbol;
		this.getprice=getprice;
		this.thumbnailUrl = thumbnailUrl;
		this.thumbnailUrl1=thumbnailUrl1;
		this.created=created;
		this.userby=userby;
		this.total=total;
		this.check=check;
		this.step=step;
		this.roomtype=roomtype;
		this.city=city;
		this.nguest=nguest;
		this.symbol1=symbol1;
		this.topay=topay;
		this.tripstatus=tripstatus;
		this.resid=resid;
		this.userto=userto;
		this.isread=isread;
		this.id1=id1;
		this.profilepic=profilepic;
		this.guest=guest;
		this.message=message;
		this.username=username;
		
	}
	 //Detailed list space
	 
	public String getid() {
		return getid;
	}
	public void getid(String id) {
		this.getid = id;
		System.out.println("GET ID**************"+getid);

	}
	
	public String guest() {
		return guest;
	}
	public void guest(String gues) {
		this.guest = gues;
		System.out.println("GET ID**************"+getid);

	}
	
	public String username() {
		return username;
	}
	public void username(String usern) {
		this.username = usern;
		System.out.println("GET ID**************"+getid);

	}
	
	public String message() {
		return message;
	}
	public void message(String mess) {
		this.message = mess;
		System.out.println("GET ID**************"+getid);

	}
	
	public String getid1() {
		return id1;
	}
	public void getid1(String id2) {
		this.id1 = id2;
		System.out.println("GET ID**************"+getid);

	}
	
	public String profilepic() {
		return profilepic;
	}
	public void profilepic(String profile) {
		this.profilepic = profile;
		System.out.println("GET ID**************"+getid);

	}
	
	
	public String getnguest() {
		return nguest;
	}
	public void setnguest(String guest) {
		this.nguest = guest;
		System.out.println("GET ID**************"+getid);

	}
	
	public String resid() {
		return resid;
	}
	public void resid(String resi) {
		this.resid = resi;
		System.out.println("GET ID**************"+getid);

	}
	
	public String userto() {
		return userto;
	}
	public void userto(String usert) {
		this.userto = usert;
		System.out.println("GET ID**************"+getid);

	}
	
	public String isread() {
		return isread;
	}
	public void isread(String isrea) {
		this.isread = isrea;
		System.out.println("GET ID**************"+getid);

	}
	
	public String gettopay() {
		return topay;
	}
	public void settopay(String topa) {
		this.topay = topa;
		System.out.println("GET ID**************"+getid);

	}
	
	public String getsymbol1() {
		return symbol1;
	}
	public void setsymbol1(String symboll1) {
		this.symbol1 = symboll1;
		System.out.println("GET ID**************"+getid);

	}
	
	public String getcity() {
		return city;
	}
	public void getcity(String cit) {
		this.city = cit;
		System.out.println("GET ID**************"+getid);

	}
	
	public String total() {
		return total;
	}
	public void total(String tot) {
		this.total = tot;
		System.out.println("GET ID**************"+total);

	}
	
	public String tripstatus() {
		return tripstatus;
	}
	public void tripstatus(String tripss) {
		this.tripstatus = tripss;
		System.out.println("GET ID**************"+total);

	}
	
	public String check() {
		return check;
	}
	public void check(String chk) {
		this.check = chk;
		System.out.println("GET ID**************"+total);

	}
	
	public String step() {
		return step;
	}
	public void step(String stp) {
		this.step = stp;
		System.out.println("GET ID**************"+total);

	}
	
	
	public String roomtype() {
		return roomtype;
	}
	public void roomtype(String room) {
		this.roomtype = room;
		System.out.println("GET ID**************"+total);

	}
		
	
	public String getuserid() {
		return getuserid;

	}
	public void getuserid(String userid) {
		this.getuserid = userid;
		System.out.println("GETUSER ID**************"+getuserid);
	}
//*****address****************************	
	
	public String getaddress() {
		return getaddress;
	}
	public void getaddress(String address) {
		this.getaddress = address;
	}
	
	public void setaddress(String address) {
		this.getaddress = address;
	}
//**************price*******************	
	public String getprice() {
		return getprice;
	}
	public void getprice(String price) {
		this.getprice = price;
	}
	
	//**************symbol*******************	
	
	public String getsymbol() {
		return getsymbol;
	}
	public void setsymbol(String symbol) {
		this.getsymbol = symbol;
	}
	//**************title*******************		 
	public String getTitle() {
		return title;
	}
	public void setTitle(String name) {
		this.title = name;
	}

//**************bg_image*******************
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}
	 
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
		
	}
	
//**************profile image*******************
	public String getThumbnailUrl1() {
		return thumbnailUrl1;
	}
	 
	public void setThumbnailUrl1(String thumbnailUrl1) {
		this.thumbnailUrl1= thumbnailUrl1;
	}
	//**************status*******************
		public String getstatus() {
			return status;
		}
		 
		public void setstatus(String status) {
			this.status= status;
		}
		//**************check in date*******************
		public String getcheckin() {
			return checkin;
		}
		 
		public void setcheckin(String checkin) {
			this.checkin= checkin;
		}
		//**************check out date*******************
		public String getcheckout() {
			return checkout;
		}
		 
		public void setcheckout(String checkout) {
			this.checkout= checkout;
		}
		//**************created date *******************
				public String getcreated() {
					return created;
				}
				 
				public void setcreated(String created) {
					this.created= created;
				}
				//**************user by *******************
				public String getuserby() {
					return userby;
				}
				 
				public void getuserby(String userby) {
					this.userby= userby;
				}
	 
}
