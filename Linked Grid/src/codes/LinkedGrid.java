package codes;

public class LinkedGrid 
{
	private Node first;
	private int dimension;
	
	public LinkedGrid(int dimension)
	{
		this.dimension = dimension;
		
		//Making the first node
		Node temp = new Node((int)(Math.random()*6+1));
		first = temp;
		first.setUp(null);
		first.setLeft(null);
		
		Node columnMarker = first;
		Node rowMarker = first;
		
		//Making the rest of the row
		for(int x = 0; x < dimension-1; x++)
		{
			temp = new Node((int)(Math.random()*6+1));
			temp.setLeft(columnMarker);
			temp.setUp(null);
			columnMarker.setRight(temp);
			columnMarker = temp;
		}
		
		for(int y = 0; y < dimension-1; y++)
		{
			temp = new Node((int)(Math.random()*6+1));
			columnMarker = temp;
			temp.setUp(rowMarker);
			temp.setLeft(null);
			rowMarker.setDown(temp);
			
			for(int x = 0; x<dimension-1; x++)
			{
				temp = new Node((int)(Math.random()*6+1));
				temp.setLeft(columnMarker);
				columnMarker.setRight(temp);
				columnMarker.getUp().getRight().setDown(temp);
				temp.setUp(columnMarker.getUp().getRight());
				columnMarker = temp;
			}
			
			rowMarker = rowMarker.getDown();
		}
	}
				
	public void display()
	{
		Node temp = first;
		Node rowMarker = first;
		
		while(temp!=null)
		{
			while(temp!=null)
			{
				if(temp.getData() < 10)
					System.out.print(temp.getData() + "   ");
				else if (temp.getData() < 100)
					System.out.print(temp.getData() + "  ");
				else
					System.out.print(temp.getData() + " ");
				
				temp = temp.getRight();
			}
				System.out.println();
				rowMarker = rowMarker.getDown();
				temp = rowMarker;
		}
	}
	
	public boolean winCondition()
	{
		Node temp = first;
		Node rowMarker = first;
		int data = temp.getData();	//stores the data from first also called temp into a variable to be later used in a comparison
		int counter = 0;
		
		while (temp != null)		//checks to see if the node isn't null going down
		{
			while (temp != null)	//checks to see if the node isn't null going right
			{
				if (data == temp.getData())		//if the data from first is equal to the data from temp
					counter++;			//add one to count
				
				temp = temp.getRight();		//change temp to the node on the right of the original temp
			}
			
			rowMarker = rowMarker.getDown();	//after the entire row is done checking, move on to the next row
			temp = rowMarker;		//set temp to the new row marker
		}
		
		if (counter == (dimension*dimension))		//if counter is equal to the amount of nodes in the grid
		{
			return true;			//return true
		}
		
		return false;			//otherwise return false
	}
	
	public void changeData (int data, Node temp)
	{
		int originalData = temp.getData();		//stores the original data into a variable for comparison with other nodes later on
		
		temp.setData(data);		//changes the current node to the new data
		
		if (temp.getRight() != null)	//checks to see if the node to the right is null or not
			if (temp.getRight().getData() == originalData)	//if the node is not null, and it is the same as the original data in the original node
				changeData(data,temp.getRight());		//runs the method again to change the new node and check around the new node
		
		if (temp.getDown() != null)		//repeats the same thing as above with the node below temp
			if (temp.getDown().getData() == originalData)
				changeData(data,temp.getDown());
		
		if (temp.getLeft() != null)		//repeats the same thing as above with the node to the left of temp
			if (temp.getLeft().getData() == originalData)
				changeData(data,temp.getLeft());
		
		if (temp.getUp() != null)		//repeats the same thing as above with the node above temp
			if (temp.getUp().getData() == originalData)
				changeData(data,temp.getUp());

		winCondition();		//checks to see if the grid is only one number or not before the lives run out
	}
	
	public Node getFirst() 
	{
		return first;
	}
	
	public void setFirst(Node first) 
	{
		this.first = first;
	}
				
	public int getDimension() 
	{
		return dimension;
	}

	public void setDimension(int dimension)
	{
		this.dimension = dimension;
	}
}


