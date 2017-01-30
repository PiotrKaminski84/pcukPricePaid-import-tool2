package pl.pmk.bussines;

import java.util.List;

public interface PostCodeService {

	public List<String> postCodesWithinRadious(String postcode, double radious);
	public void loadPostCodesFromFile(String path);

	public List<PostCode> getListOfPostCodes();
}
