public class LabyrinthLOOP {
	protected Datas labyrinth[][];
	protected Datas start,end;
	protected LinkedStack<Datas> stack;
	
	public Labyrinth(Datas labyrinth[][],Datas start,Datas end)
	{
		this.labyrinth=labyrinth;
		this.start=new Datas(start.isWhite,start.x,start.y);
		this.end=new Datas(end.isWhite,end.x,end.y);
	}
	
	public void Find()
	{
		int cout=0;
		int num=0;
		Datas data=start;
		Datas last=start;
		stack=new LinkedStack<Datas>();
		
		for(int i=0;i<labyrinth.length;i++)
			for(int j=0;j<labyrinth[0].length;j++)
			{
				if((j!=this.labyrinth[0].length-1&&this.labyrinth[i][j+1].isWalk==false&&this.labyrinth[i][j+1].isWhite==true))
					cout++;
				if(i!=0&&this.labyrinth[i-1][j].isWalk==false&&this.labyrinth[i-1][j].isWhite==true)
					cout++;
				if(j!=0&&this.labyrinth[i][j-1].isWalk==false&&this.labyrinth[i][j-1].isWhite==true)
					cout++;
				if(i!=this.labyrinth.length-1&&this.labyrinth[i+1][j].isWalk==false&&this.labyrinth[i+1][j].isWhite==true)
					cout++;
				if(cout>=3)
					labyrinth[i][j].isfork=true;
				cout=0;
			}
		
		if(data.y!=this.labyrinth[0].length-1&&this.labyrinth[data.x][data.y+1].isWalk==false&&this.labyrinth[data.x][data.y+1].isWhite==true)
		{
			stack.push(data);
			data.isWalk=true;
			last=data;
			data=this.labyrinth[data.x][data.y+1];
		}
		else if(data.x!=0&&this.labyrinth[data.x-1][data.y].isWalk==false&&this.labyrinth[data.x-1][data.y].isWhite==true)
		{
			stack.push(data);
			data.isWalk=true;
			last=data;
			data=this.labyrinth[data.x-1][data.y];
		}
		else if(data.x!=this.labyrinth.length-1&&this.labyrinth[data.x+1][data.y].isWalk==false&&this.labyrinth[data.x+1][data.y].isWhite==true)
		{
			stack.push(data);
			data.isWalk=true;
			last=data;
			data=this.labyrinth[data.x+1][data.y];
		}
		else if(data.y!=0&&this.labyrinth[data.x][data.y-1].isWalk==false&&this.labyrinth[data.x][data.y-1].isWhite==true)
		{
			stack.push(data);
			data.isWalk=true;
			last=data;
			data=this.labyrinth[data.x][data.y-1];
		}

		while(!stack.isEmpty())
		{
			if(data.x==end.x&&data.y==end.y)
			{
				num++;
				LinkedStack stack2=new LinkedStack();
				String s="正确通路（";
				String str="";
				while(stack.isEmpty()==false)
				{
					Datas dataa=stack.pop();
					str="["+dataa.x+","+dataa.y+"]"+str;
					stack2.push(dataa);
				}
				str=str+"["+data.x+","+data.y+"]";
				System.out.println(s+str+"）");
				while(!stack2.isEmpty())
				{
					stack.push((Datas) stack2.pop());
				}
				last=data;
				data=stack.pop();
				data.isWalk=false;
				last=data;
				data=stack.pop();
				data.isWalk=false;
			}
			
			if(data.y!=this.labyrinth[0].length-1&&this.labyrinth[data.x][data.y+1].isWalk==false&&this.labyrinth[data.x][data.y+1].isWhite==true&&!(last.x==data.x&&last.y==(data.y+1)))
			{
				stack.push(data);
				data.isWalk=true;
				last=data;
				data=this.labyrinth[data.x][data.y+1];
			}
			else if(data.x!=0&&this.labyrinth[data.x-1][data.y].isWalk==false&&this.labyrinth[data.x-1][data.y].isWhite==true&&!(last.x==(data.x-1)&&last.y==data.y))
			{
				stack.push(data);
				data.isWalk=true;
				last=data;
				data=this.labyrinth[data.x-1][data.y];
			}
			else if(data.x!=this.labyrinth.length-1&&this.labyrinth[data.x+1][data.y].isWalk==false&&this.labyrinth[data.x+1][data.y].isWhite==true&&!(last.x==(data.x+1)&&last.y==data.y))
			{
				stack.push(data);
				data.isWalk=true;
				last=data;
				data=this.labyrinth[data.x+1][data.y];
			}
			else if(data.y!=0&&this.labyrinth[data.x][data.y-1].isWalk==false&&this.labyrinth[data.x][data.y-1].isWhite==true&&!(last.x==data.x&&last.y==(data.y-1)))
			{
				stack.push(data);
				data.isWalk=true;
				last=data;
				data=this.labyrinth[data.x][data.y-1];
			}	
			else
			{
				Datas temp=data;
				if(data.isfork==true)
				{
					temp=stack.peak();	
					Datas temp2=stack.peak();
					if(temp2.isExerting==false)
					{
						if(data.x-2>=0&&labyrinth[data.x-2][data.y].isExerting==false)
							labyrinth[data.x-1][data.y].isWalk=false;
						if(data.x+2<labyrinth.length&&labyrinth[data.x+2][data.y].isExerting==false)
							labyrinth[data.x+1][data.y].isWalk=false;
						if(data.y-2>=0&&labyrinth[data.x][data.y-2].isExerting==false)
							labyrinth[data.x][data.y-1].isWalk=false;
						if(data.y+2<labyrinth[0].length&&labyrinth[data.x][data.y+2].isExerting==false)
							labyrinth[data.x][data.y+1].isWalk=false;
						data.isExerting=false;
					}
					else
					{
						labyrinth[data.x-1][data.y].isWalk=false;
						labyrinth[data.x+1][data.y].isWalk=false;
						labyrinth[data.x][data.y-1].isWalk=false;
						labyrinth[data.x][data.y+1].isWalk=false;
						data.isExerting=false;
					}
					stack.push(temp2);
					stack.push(temp);
					last=data;
					last.isWalk=false;
					data=stack.pop();
					if(data!=null)
					{
						while(data.isfork==false)
						{
							last=data;
							last.isWalk=false;
							data=stack.pop();
							if(data==null)
								break;
						}
						if(data!=null)
						{	
							last.isWalk=true;
							data.isExerting=true;
						}
					}
				}
				else
				{
					last=data;
					last.isWalk=false;
					data=stack.pop();
					if(data!=null)
					{
						while(data.isfork==false)
						{
							last=data;
							last.isWalk=false;
							data=stack.pop();
							if(data==null)
								break;
						}
						
						if(data!=null)
						{	
							last.isWalk=true;
							data.isExerting=true;
						}
					}
				}
			}
		}
		System.out.println("一共有"+num+"条。");
		for(int i=0;i<this.labyrinth.length;i++)
		{
			for(int j=0;j<this.labyrinth[0].length;j++)
				System.out.print(((this.labyrinth[i][j].x==start.x&&this.labyrinth[i][j].y==start.y)
						||(this.labyrinth[i][j].x==end.x&&this.labyrinth[i][j].y==end.y)
						?((this.labyrinth[i][j].x==start.x&&this.labyrinth[i][j].y==start.y)
								?"   start":"     end"):"\t")
						+"["+i+","+j+"]"+(this.labyrinth[i][j].isWhite==true?"白":"黑")+"\t");
			System.out.println();
			System.out.println();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Datas  data[][]=new Datas[8][12];
		boolean bool[][]={
				{false,false,false,false,false,false,false,false,false,false,false,false},
				{true,true,false,true,true,true,true,true,true,true,true,false},
				{false,true,false,true,false,false,false,true,false,false,false,false},
				{false,true,true,true,true,true,true,true,true,true,true,false},
				{false,true,false,false,false,false,false,true,false,false,true,true},
				{false,true,true,true,true,true,true,true,false,false,true,false},
				{false,true,false,false,false,false,false,true,true,true,true,false},
				{false,false,false,false,false,false,false,false,false,false,false,false}};
		for(int i=0;i<8;i++)
			for(int j=0;j<12;j++)
			{
				data[i][j]=new Datas(bool[i][j],i,j);
			}
		Labyrinth laby=new Labyrinth(data,data[4][11],data[1][0]);
		laby.Find();
	}
}



class Datas {

		public int x,y;
		public boolean isWhite;
		public boolean isWalk;
		public boolean isfork;
		public boolean isExerting;
		
		public Datas(boolean isWhite,int x,int y)
		{
			this.isWhite=isWhite;
			this.x=x;
			this.y=y;
		}
}
