package InteviewExam.interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Arlan {

	public static void main(String[] args) {
		new Arlan().solution(
				"photo.jpg, Warsaw, 2013-09-05 14:08:15\njohn.png, London, 2015-06-20 15:13:22\nmyFriends.png, Warsaw, 2013-09-05 14:07:13\nEiffel.jpg, Paris, 2015-07-23 08:03:02\npisatower.jpg, Paris, 2015-07-22 23:58:59\nBOB.jpg, London, 2015-08-05 00:02:03\nnotredame.png, Paris, 2015-09-01 12:00:00\nme.jpg, Warsaw, 2013-09-06 15:40:22\na.png, Warsaw, 2016-02-13 13:33:50\nb.jpg, Warsaw, 2016-01-02 15:12:22\nc.jpg, Warsaw, 2016-01-02 14:34:30\nd.jpg, Warsaw, 2016-01-02 15:15:01\ne.png, Warsaw, 2016-01-02 09:49:09\nf.png, Warsaw, 2016-01-02 10:55:32\ng.jpg, Warsaw, 2016-02-29 22:13:11");
	}

	public void solution(String str) {
		String[] input = str.split("\n");
		 
		// parse then put into list
		List<Photo> lstPhoto = new ArrayList<Photo>();
		for (int i = 0; i < input.length; i++) {
		    String[] input2 = input[i].split(",");
		    
		    Photo photo = new Photo();
		    photo.setFileName(input2[0].trim());
		    photo.setLocation(input2[1].trim());
		    photo.setDateTime(input2[2].trim());
		    photo.setOriginalOrder(i+1);
		    lstPhoto.add(photo);
		}
		
		// Sort the list by Location first then by DateTime
		Collections.sort(lstPhoto, new Comparator<Photo>() {
			public int compare(Photo o1, Photo o2) {
				String x1 = o1.getLocation();
	            String x2 = o2.getLocation();
	            int sComp = x1.compareTo(x2);
	            
	            if (sComp != 0) {
	               return sComp;
	            } 

	            String y1 = o1.getDateTime();
	            String y2 = o2.getDateTime();
	            return y1.compareTo(y2);
			}
	    });
		

		int photoCtr = 1;
		Photo firstPhoto = lstPhoto.get(0);
		String locationCount = this.getRecordCount(lstPhoto, firstPhoto.getLocation()).toString();
		firstPhoto.setCounter(photoCtr++);
		firstPhoto.setPhotoCountPerLocation(locationCount);
		
		Photo comparePhoto = firstPhoto;
		for (int i = 1 ; i <lstPhoto.size(); i++) {
			Photo photo = lstPhoto.get(i);
			
			if (!photo.getLocation().equalsIgnoreCase(comparePhoto.getLocation())) {
				// reset the counter if the last and current photo is not same location
				photoCtr = 1;
				locationCount = this.getRecordCount(lstPhoto, photo.getLocation()).toString();
			}
			
			photo.setCounter(photoCtr++);
			photo.setPhotoCountPerLocation(locationCount);
			comparePhoto = photo;
		}
		
		// sort back to original order
		Collections.sort(lstPhoto, new Comparator<Photo>() {
			public int compare(Photo o1, Photo o2) {
	            Integer y1 = o1.getOriginalOrder();
	            Integer y2 = o2.getOriginalOrder();
	            return y1.compareTo(y2);
			}
	    });
		
		for (Photo photo : lstPhoto) {
			String location = photo.getLocation();
			String countSuffix = String.format("%0" + photo.getPhotoCountPerLocation().toString().length() + "d", photo.getCounter());
			String fileNameExtension = photo.getFileName().substring(photo.getFileName().lastIndexOf("."));
			
		    System.out.println(location+countSuffix+fileNameExtension);
		}
	}
	
	// panget na code 
	public Integer getRecordCount(List<Photo> lstPhoto, String location) {
		Integer count = 0;
		for (int i = 0; i < lstPhoto.size(); i++) {
			Photo photo = lstPhoto.get(i);
			
			if (location.equalsIgnoreCase(photo.getLocation())) {
				count++;
			}
		}
		return count;
	}
}

class Photo {

	private String fileName;

	private String location;

	private String dateTime;
	
	private Integer originalOrder;
	
	private Integer counter;
	
	private String photoCountPerLocation;

	public String getPhotoCountPerLocation() {
		return photoCountPerLocation;
	}

	public void setPhotoCountPerLocation(String photoCountPerLocation) {
		this.photoCountPerLocation = photoCountPerLocation;
	}

	public Integer getCounter() {
		return counter;
	}

	public void setCounter(Integer counter) {
		this.counter = counter;
	}

	public Integer getOriginalOrder() {
		return originalOrder;
	}

	public void setOriginalOrder(Integer originalOrder) {
		this.originalOrder = originalOrder;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		sb.append("{").append("fileName=").append(this.fileName).append(", ");
		sb.append("location=").append(this.location).append(", ");
		sb.append("dateTime=").append(this.dateTime).append(", ");
		sb.append("originalOrder=").append(this.originalOrder).append(", ");
		sb.append("counter=").append(this.counter).append(", ");
		sb.append("photoCountPerLocation=").append(this.photoCountPerLocation).append("}");
		return sb.toString();
	}
}
