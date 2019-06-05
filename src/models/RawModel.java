package models;

public class RawModel {
	
	private int VaoId;
	private int vertexCount;
	
	public RawModel(int VaoId,int vertexCount) {
		
		this.VaoId=VaoId;
		this.vertexCount=vertexCount;
	}

	public int getVaoId() {
		return VaoId;
	}

	public int getVertexCount() {
		return vertexCount;
	}
	
	

}
