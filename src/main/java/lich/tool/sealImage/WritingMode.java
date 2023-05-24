package lich.tool.sealImage;
/**
 * 书写模式
 * @author lich
 */
public enum WritingMode{	
		/**
		 * 左上起始，横向书写
		 */
		LEFT_TOP_HORIZONTAL(1,1,true),
		/**
		 * 右上起始，横向书写
		 */
		RIGHT_TOP_HORIZONTAL(-1,1,true),
		/**
		 * 左下起始，横向书写
		 */
		LEFT_BUTTOM_HORIZONTAL(1,-1,true),
		/**
		 * 右下起始，横向书写
		 */
		RIGHT_BUTTOM_HORIZONTAL(-1,-1,true),
		/**
		 * 左上起始，纵向书写
		 */
		LEFT_TOP_VERTICAL(1,1,false), 
		/**
		 * 右上起始，纵向书写
		 */
		RIGHT_TOP_VERTICAL(-1,1,false),
		/**
		 * 左下起始，纵向书写
		 */
		LEFT_BUTTOM_VERTICAL(1,-1,false),
		/**
		 * 右下起始，纵向书写
		 */
		RIGHT_BUTTOM_VERTICAL(-1,-1,false);
		private int ivX;
		private int ivY;
		private boolean xPrior;	
		WritingMode(int ivX,int ivY,boolean xPrior){
			this.ivX=ivX;
			this.ivY=ivY;
			this.xPrior=xPrior;
		}
		public int getIvX() {
			return ivX;
		}
		public int getIvY() {
			return ivY;
		}
		public boolean isxPrior() {
			return xPrior;
		}
		
	}

