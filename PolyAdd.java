import java.util.ArrayList;
/*
 * author: Carl Estabrook
 * class: algorithms
 * date: 3/2011
 */
public class PolyAdd 
{
	
	// preprocess time = theta(n): n = size of initial statement string
	// adding time = theta(m + 1): m = largest exponent value from both polynomials
	
	// adds two polynomials by placing values in array.
	// Array index value corresponds with exponent value
	public static int index ;
	
	public static int getExp(int index1, String sub)
	{
	     int exp = 1;
	     String ex;
	     char temp1;
	     
	     for(int k = 0; k<sub.length(); k++)
		   {
			   temp1 = sub.charAt(index1 +(k+1));
			      
			   if(temp1 == '-' || temp1 == '+'|| (sub.indexOf(temp1, index1)+1) == sub.length())
			   {
				   if((sub.indexOf(temp1, index1)+1) == sub.length())
				   {
					   ex = sub.substring(index1+1, (index1+1) +k+1);
					   exp = Integer.parseInt(ex);
					   index = sub.indexOf(temp1, index1);
					   return exp;
				   }
				   
				   ex = sub.substring(index1+1, index1 +(k+1));
				   exp = Integer.parseInt(ex);
				   index = sub.indexOf(temp1, index1);
				   return exp;
			   }   
		   }
	     return exp;
	}
	//parse string poly and put in arraylist form
	public static void parsePoly(String poly, ArrayList<Integer> list )
	{
		index = 0;
		String sub = poly;
		
		for(;;)
		{
			sub = sub.substring(index);
			index = sub.indexOf("^", 0);
			
			if(index == -1)
			{
				for(;;)
				{
					int ind, numb;
					ind = sub.indexOf("x");
					char temp1;
					String  temp2;
					if(ind == -1)
					{
						temp1 = sub.charAt(0);
						temp2 = sub.substring(1);
						
						if(temp1 == '-')
						{
							numb = Integer.parseInt(temp2);
							list.set(0,new Integer(-numb));
						}
						else if(temp1 == '+')
						{
							numb = Integer.parseInt(temp2);
							list.set(0, new Integer(numb));
						}
						else
						{
							numb = Integer.parseInt(sub.substring(0));
							list.set(0, new Integer(numb));
						}
						
						return;
					}
					else
					{
					   if(ind == 0)
						   list.set(1,new Integer(1));
					   else
					   {
						   temp1 = sub.charAt(ind - 1);
						   if(temp1 == '-')
							   list.set(1, new Integer(-1));
						   else if(temp1 == '+')
							   list.set(1, new Integer(1));
						   else
						   {
							   temp1 = sub.charAt(0);
							  
						      if(temp1 == '-')
						      {
						    	  numb =  Integer.parseInt(sub.substring(1, ind));					    	  
						    	  list.set(1, new Integer(-numb));
						      }
						      else if (temp1 == '+')
						      {
						    	  numb =  Integer.parseInt(sub.substring(1, ind));
						    	  list.set(1, numb);
						      }
						      else
						      {
						    	  numb =  Integer.parseInt(sub.substring(0, ind));
						    	  list.set(1, numb); 
						      }
						   }
						   
						   if(ind+1 == sub.length())
						   {							  
							   return;
						   }
					   }	
					  
					       sub = sub.substring(ind+1);
					}
				}				
			}
			else
			{
			   int temp, exp = 1, numb = 1;
			   char temp1, temp2;
		
			   exp = getExp(index, sub);
			   
			   temp = sub.indexOf("x");
			   if(temp > 0)
			   {
				   temp2 = sub.charAt(temp-1);
				   if(temp2 == '-')
					   list.set(exp,new Integer(-1));
				   else if (temp2 == '+')
					   list.set(exp, new Integer(1));
				   else
				   {
					   temp1 = sub.charAt(0);
					   
				      if(temp1 == '-')
				      {
				    	  numb =  Integer.parseInt(sub.substring(1, temp));
				    	  list.set(exp, -numb);
				      }
				      else if (temp1 == '+')
				      {
				    	  numb =  Integer.parseInt(sub.substring(1, temp));
				    	  list.set(exp, numb);
				      }
				      else
				      {
				    	  numb =  Integer.parseInt(sub.substring(0, temp));
				    	  list.set(exp, numb);  
				      }  
				   }   
			   }
			   else
				   list.set(exp, new Integer(1));
			   
			   if((index+1) == sub.length())
				   return;
			     
			}	
		}	
	}
	
	public static String addPoly( ArrayList<Integer> A,  ArrayList<Integer> B)
	{
		
		// adding time = theta(m + 1) m = largest exponent value from both polynomials
		
		String temp= "";
		
		for(int i = 0; i < A.size(); i++)
		{
			int num = A.get(i) + B.get(i);
			
			if(i==0)
			{
				if(num>0)
				   temp = temp+"+"+num;
				
				if(num<0)
					temp = temp+num;
			}
			else if(i==1)
			{
				if(num>0)
				{
					if(num == 1)
					   temp = "+"+"x"+temp;
					else
						temp = "+"+num+"x"+temp;
				}
					
				if(num<0)
				{
					if(num == -1)
					   temp = "-"+"x"+temp;
					else
						temp = num+"x"+temp;
				}
			}
			else
			{
				if(num>0)
				{
					if(num == 1)
					   temp = "+"+"x^"+i+temp;
					else
						temp = "+"+num+"x^"+i+temp;
				}
					
				if(num<0)
				{
					if(num == -1)
					   temp = "-"+"x^"+i+temp;
					else
						temp = num+"x^"+i+temp;
				}
			}
		}
		return temp;
	}
	
	public static void main(String [] args)
	{	
		String statement;
		int index1, index2;
		String a, b;
		char num;
		
		ArrayList<Integer> one = new ArrayList<Integer>();
		ArrayList<Integer> two = new ArrayList<Integer>();
		// test cases
		ArrayList<String> test = new ArrayList<String>();
		test.add("(x^3+5x^2-3x+7) + (4x^5-2x^2+1)");
		test.add("(-7) + (-4x+1)");
		test.add("(-56x^30+5x^7+x^2-3x) + (4x^7-2x^2+1)");
		test.add("(5) + (4x^5-2x^2+1)");
		test.add("(-5x^5) + (4x^5-2x^2+1)");
		
		
		for(int i =0; i < test.size(); i++)
		{
			statement ="";
			a="";
			b="";
			one.clear();
			two.clear();
			statement = test.get(i);
			
			// parse initial string for both polynomials
			index1 = statement.indexOf(")");
			index2 = statement.indexOf("(", index1);
			a = statement.substring(1, index1);
			b = statement.substring(index2 + 1,statement.length() - 1);
			
			//find largest exponent value and set arraylists
			index1 = a.indexOf("^");
			index2 = b.indexOf("^");
			if(index1 == -1)
				index1 = 2;
			else
				 index1 = getExp(index1, a);
			
			if(index2 == -1)
				index2 = 2;
			else
				 index2 = getExp(index2, b);
				
			
			if(index1 >= index2)
			{
				for(int k =0; k<index1+1; k++)
				{
					one.add(k, 0);
					two.add(k, 0);
				}	
			}
			else
			{
				for(int k = 0; k< index2+ 1; k++)
				{
					one.add(k, 0);
					two.add(k, 0);
				}
			}
			//parse each polynomial and place terms in arraylists
			parsePoly(a, one);
			parsePoly(b, two);
			// Output initial statement and answer
			System.out.println(statement+" = "+addPoly(one, two));	
		}		
	}
}
