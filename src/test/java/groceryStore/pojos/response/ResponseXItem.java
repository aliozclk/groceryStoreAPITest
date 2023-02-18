package groceryStore.pojos.response;

import lombok.Data;

@Data
public class ResponseXItem{
	private String name;
	private boolean inStock;
	private int id;
	private String category;
}