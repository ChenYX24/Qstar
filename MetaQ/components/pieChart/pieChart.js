
import * as echarts from 'echarts'  
  
class EchartsPie {  
  constructor(id, width, height, option) {  
	this.id = id  
	this.width = width  
	this.height = height  
	this.option = option  
	this.chart = null  
  }  
  
  init() {  
	if (this.chart) {  
	  this.chart.dispose()  
	}  
  
	this.chart = echarts.init(document.getElementById(this.id))  
  
	this.chart.setOption(this.option)  
  
	this.chart.on('mouseup', (params) => {  
		console.log(params)
	  this.chart.dispatchAction({  
		type: 'pieToggleSelect',  
		dataIndex: params.dataIndex  
	  })  
	})  
	  
	this.chart.on('touchend', (params) => {  
		console.log(params)
	  this.chart.dispatchAction({  
		type: 'pieToggleSelect',  
		dataIndex: params.dataIndex  
	  })  
	})  
 
  }  
  
  setOption(option) {  
	this.option = option 
	this.init()  
  }  
}  
  
export default EchartsPie  

