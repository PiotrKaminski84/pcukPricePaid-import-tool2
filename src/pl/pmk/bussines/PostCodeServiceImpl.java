package pl.pmk.bussines;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PostCodeServiceImpl implements PostCodeService{

	private List<PostCode> listOfPostCodes=new ArrayList<>();
	
	@Override
	public List<String> postCodesWithinRadious(String postcode, double radious) {
		PostCode pc = listOfPostCodes.stream().filter(a->a.getPostCode().equalsIgnoreCase(postcode)).findFirst().get();			
		return listOfPostCodes.stream().filter(p->p.distanceFrom(pc.getLatitude(),pc.getLongitude())<radious).map(a->a.getPostCode()).collect(Collectors.toList());		
	}

	@Override
	public void loadPostCodesFromFile(String path) {		
		try{
			BufferedReader br = new BufferedReader(new FileReader(new File(path)));
			br.readLine();//skip header
			String buffer = br.readLine();
			PostCode postCode;
			while(buffer!=null){
				postCode = new PostCode(buffer);
				listOfPostCodes.add(postCode);
				buffer=br.readLine();
			}
			br.close();
		}catch (IOException e){
			System.out.println("problem reading File");			
		}
	}

	@Override
	public List<PostCode> getListOfPostCodes() {		
		return listOfPostCodes;
	}

	
}
