<template>
  <view class="Block">
    <view class="topBox">
      <view class="title">{{ title }}</view>
      <view class="type">[{{ CType }}]</view>
    </view>
    <swiper
      class="middleBox"
      @change="onSwiperChange"
      :current="currentSwiper"
      :loop="false"
    >
      <swiper-item class="chart" v-for="(item, index) in SData" :key="index" style="overflow-y: scroll;">
	          <view v-if="index === 0" >
	            <!-- 单选题列表 -->
	            <my-table :Data="chartData"></my-table>
	          </view>
	          <view v-if="index === 1" class="chartContainer">
	            <!-- 饼状图 -->
					<echarts :options="pieOption" ></echarts>  <!-- @click-chart="handleClickChart" -->
	          </view>
			  <view v-if="index === 2" class="chartContainer">
				  <!-- 环状图 -->
					<echarts :options="huanOption" ></echarts>  
			  </view>
			  <view v-if="index === 3" class="chartContainer">
			  		<echarts :options="barOption" ></echarts>  
			  </view>
			  <view v-if="index === 4" class="chartContainer">
			  		<echarts :options="tiaoOption" ></echarts>  
			  </view>
			  <view v-if="index === 5" class="chartContainer">
			  		<echarts :options="lineOption" ></echarts>  
			  </view>

	  </swiper-item>
    </swiper>
    <view class="bottomBox">
      <view
        v-for="(item, index) in SData"
        :key="index"
        @tap="onBottomItemClick(index)"
        :class="{ 'bottomItem': true, 'ellipse': currentSwiper === index }"
      >
        {{ SData[index] }}
      </view>
    </view>
  </view>


</template>
<script>
	import echarts from '/components/echarts/echarts.vue';
	import MyTable from '/components/myTable/myTable.vue';
	export default {
		name:"QBlock",
		props: {
			title: {
				type: String,
				default:''
			},
			type:{
				type:String,
				default:''
			},
			chartData:{
				type:Array,
				default:[]
			}
		},
		components:{
			MyTable  
		},
		data(){
			return {
				pushText:"已发布",
				CType:'',
				SData:["列表","饼状","环状","柱状","条形","折线"],
				displayText: "列表",
				currentSwiper: 0,
				// chartData:[
				//   { value: 1048, name: 'Search Engine'},
				//   { value: 735, name: 'Direct'},
				//   { value: 580, name: 'Email'},
				//   { value: 484, name: 'Union Ads'},
				//   { value: 300, name: 'Video Ads'},
				//   { value: 300, name: 'Vdeo'},
				//   { value: 300, name: 'Vieo'},
				//   { value: 300, name: 'Vido'},
				//   { value: 300, name: 'Vide'},
				// ],
				tiaoOption:{
					toolbox: {
					    feature: {
					      saveAsImage: {}
					    }
					  },
					tooltip: {
								trigger: 'item',
								axisPointer: {
								  type: 'shadow'
								},
								position:'auto'
							  },
								grid: {  
								  top: 40,  
								  bottom: 30,  
								  left: 100,  
								  right: 30,  
								},  
							  xAxis: {
								  type: 'value',
								  
							  },
							  yAxis: {								
								  type: 'category',
								  data: [],
								  axisLabel: {
								    interval: 0,  
								    rotate: 30,
									showMaxLabel:true,
									formatter: function(value) {  
												const maxLength = 9;  
												if (value.length > maxLength) {  
												  return value.substr(0, maxLength) + '...';  
												} else {  
												  return value;  
												}  
											  },  
								  }
							  },
							  series: [
								{
								  type: 'bar',
								  barHeight: '60%',
								  data: [],
								  
								  itemStyle :{
									  emphasis: {						
									    label: {
									      show: true,
									      fontSize: 16,
									      color:'rgba(196, 140, 255, 1)',
										  position:'right',
										  // offset: [0, -15]
										  
									    }
									  },
									  borderRadius: [0, 999, 999, 0],
									  color:{
											  type: 'linear',
											  x: 1,
											  y: 0,
											  x2: 0,
											  y2: 0,
											  colorStops: [{
												  offset: 0, color: 'rgba(196, 140, 255, 1)' // 0% 处的颜色
											  },{
												  offset: 0.51, color: 'rgba(235, 223, 242, 0.7)' // 100% 处的颜色
											  },{
												  offset: 0.78, color: 'rgba(232, 237, 255, 0.5)' // 100% 处的颜色
											  }, {
												  offset: 1, color: 'rgba(255, 255, 255, 0)' // 100% 处的颜色
											  }],
											  global: false // 缺省为 false
											}
								  }
								}
							  ],
						dataZoom: [  
					  {  
					    type: 'slider',  
					    yAxisIndex: 0, // Apply dataZoom to the xAxis at index 0  
					    start: 0, // Initial start percentage  
					    end: 100, // Initial end percentage  
					    showDataShadow: true, // Disable data shadow  
					    showDetail: true, // Disable detail tooltip
						height:'80%',
						left:'0',
						bottom:'20',
						orient: 'vertical', 
					  },
						{
						  type: 'inside',
						  orient: 'vertical', 
						  start: 0,
						  end: 100
						},
					],
				},
				barOption:{
					toolbox: {
					    feature: {
					      saveAsImage: {}
					    }
					  },
							tooltip: {
								trigger: 'item',
								axisPointer: {
								  type: 'shadow'
								},
								position:'auto'
							  },
								grid: {  
								  top: 40,  
								  bottom: 100,  
								  left: 50,  
								  right: 0,  
								},  
							  xAxis: {
								
								  type: 'category',
								  data: [],
								  axisLabel: {
								    interval: 0,  
								    rotate: 30,
									showMaxLabel:true,
									formatter: function(value) {  
									            const maxLength = 9;  
									            if (value.length > maxLength) {  
									              return value.substr(0, maxLength) + '...';  
									            } else {  
									              return value;  
									            }  
									          },  
								  }
							  },
							  yAxis: {								
								  type: 'value',
							  },
							  series: [
								{
								  type: 'bar',
								  barWidth: '60%',
								  data: [],
								  
								  itemStyle :{
									  emphasis: {						
									    label: {
									      show: true,
									      fontSize: 16,
									      color:'rgba(196, 140, 255, 1)',
										  position:'top'
									    }
									  },
									  borderRadius: [999, 999, 0, 0],
									  color:{
											  type: 'linear',
											  x: 0,
											  y: 0,
											  x2: 0,
											  y2: 1,
											  colorStops: [{
												  offset: 0, color: 'rgba(196, 140, 255, 1)' // 0% 处的颜色
											  },{
												  offset: 0.51, color: 'rgba(235, 223, 242, 0.7)' // 100% 处的颜色
											  },{
												  offset: 0.78, color: 'rgba(232, 237, 255, 0.5)' // 100% 处的颜色
											  }, {
												  offset: 1, color: 'rgba(255, 255, 255, 0)' // 100% 处的颜色
											  }],
											  global: false // 缺省为 false
											}
								  }
								}
							  ],
						dataZoom: [  
				      {  
				        type: 'slider',  
				        xAxisIndex: 0, // Apply dataZoom to the xAxis at index 0  
				        start: 0, // Initial start percentage  
				        end: 100, // Initial end percentage  
				        showDataShadow: true, // Disable data shadow  
				        showDetail: true, // Disable detail tooltip
						bottom:'40',
						
						right:'5',
						height:'20'
				      },
						{
						  type: 'inside',
						  start: 0,
						  end: 100
						},
				    ],
					},
					lineOption:{
						toolbox: {
						    feature: {
						      saveAsImage: {}
						    }
						  },
								tooltip: {
									trigger: 'item',
									axisPointer: {
									  type: 'shadow'
									},
									position:'auto'
								  },
									grid: {  
									  top: 40,  
									  bottom: 100,  
									  left: 50,  
									  right: 0,  
									},  
								  xAxis: {
									
									  type: 'category',
									  data: [],
									  axisLabel: {
									    interval: 0,  
									    rotate: 30,
										showMaxLabel:true,
										formatter: function(value) {  
										            const maxLength = 9;  
										            if (value.length > maxLength) {  
										              return value.substr(0, maxLength) + '...';  
										            } else {  
										              return value;  
										            }  
										          },  
									  },
									  
								  },
								  yAxis: {								
									  type: 'value',
								  },
								  series: [
									{
									  type: 'line',
									  data: [],
									        markLine: {  
									          silent: true, // 禁止交互  
									          lineStyle: {  
									            type: 'dashed', // 设置为虚线  
									          },  
									          data: [],  
									        },
									  emphasis: {
									    // disabled: true,
										scale:2,
										label:{
											fontSize:20
										}
									  },
										areaStyle: {
											        emphasis: {  
											          color:{
											            type: 'linear',
											            x: 0,
											            y: 0,
											            x2: 0,
											            y2: 1,
											            colorStops: [{
											          	  offset: 0, color: 'rgba(196, 140, 255, 1)' // 0% 处的颜色
											            },{
											          	  offset: 0.51, color: 'rgba(235, 223, 242, 0.7)' // 100% 处的颜色
											            },{
											          	  offset: 0.78, color: 'rgba(232, 237, 255, 0.5)' // 100% 处的颜色
											            }, {
											          	  offset: 1, color: 'rgba(255, 255, 255, 0)' // 100% 处的颜色
											            }],
											            global: false // 缺省为 false
											          }
											        },  
												color:{
												  type: 'linear',
												  x: 0,
												  y: 0,
												  x2: 0,
												  y2: 1,
												  colorStops: [{
													  offset: 0, color: 'rgba(196, 140, 255, 1)' // 0% 处的颜色
												  },{
													  offset: 0.51, color: 'rgba(235, 223, 242, 0.7)' // 100% 处的颜色
												  },{
													  offset: 0.78, color: 'rgba(232, 237, 255, 0.5)' // 100% 处的颜色
												  }, {
													  offset: 1, color: 'rgba(255, 255, 255, 0)' // 100% 处的颜色
												  }],
												  global: false // 缺省为 false
												}
										},
										label: {
										  show: true,
										  fontSize: 10,
										  color:'rgba(196, 140, 255, 1)',
										  position:'top'
										},

									  itemStyle :{
										  borderRadius: [999, 999, 0, 0],
										  color: 'rgba(196, 140, 255, 1)' // 0% 处的颜色
									  }
									}
								  ],
							dataZoom: [  
					      {  
					        type: 'slider',  
					        xAxisIndex: 0, // Apply dataZoom to the xAxis at index 0  
					        start: 0, // Initial start percentage  
					        end: 100, // Initial end percentage  
					        showDataShadow: true, // Disable data shadow  
					        showDetail: true, // Disable detail tooltip
							bottom:'40',
							right:'5',
							height:'20'
					      },
							{
							  type: 'inside',
							  start: 0,
							  end: 100
							},
					    ],
						},
					

			};
		},
		mounted() {
			switch (this.type){
				case 'single':
					this.CType="单选题"
					break;
				case 'multiple':
					this.CType="多选题"
					break;
				case 'blank':
					this.CType="填空题"
					break;
				default:
					break;
			}
			this.setChartOptions();
		},
		computed: {
		    end(){
		        if(this.isEnd)
				{
					return '已截止'
				}
				else{
					return '修改'
				}
		    },
			dynamicColorPalette(){
				function getLinearGradientColor(startColor, endColor, percentage) {
				  const r = Math.round(startColor.r + (endColor.r - startColor.r) * percentage);
				  const g = Math.round(startColor.g + (endColor.g - startColor.g) * percentage);
				  const b = Math.round(startColor.b + (endColor.b - startColor.b) * percentage);
				  const a = startColor.a + (endColor.a - startColor.a) * percentage;

				  return `rgba(${r}, ${g}, ${b}, ${a})`;
				}

				const colorStops = [
				  { color: { r: 22, g: 176, b: 188, a: 1 }, position: 0 },
				  { color: { r: 134, g: 226, b: 235, a: 1 }, position: 16.32 },
				  { color: { r: 162, g: 245, b: 231, a: 1 }, position: 27.02 },
				  { color: { r: 204, g: 248, b: 252, a: 1 }, position: 37.07 },
				  { color: { r: 223, g: 222, b: 251, a: 1 }, position: 51.83 },
				  { color: { r: 208, g: 194, b: 253, a: 1 }, position: 63.52 },
				  { color: { r: 202, g: 146, b: 222, a: 1 }, position: 76.61 },
				  { color: { r: 127, g: 106, b: 183, a: 1 }, position: 89.31 },
				  { color: { r: 95, g: 66, b: 173, a: 1 }, position: 100 }
				];


				const dataLength = this.chartData.length;

				const dColorPalette = [];
				for (let i = 0; i < dataLength; i++) {
				  const colorIndex = Math.floor((i / (dataLength - 1)) * (colorStops.length - 1));
				  const nextColorIndex = Math.ceil((i / (dataLength - 1)) * (colorStops.length - 1));

				  const colorStop = colorStops[colorIndex];
				  const nextColorStop = colorStops[nextColorIndex];

				  const percentage = (i / (dataLength - 1)) * 100;
				  const interpolatedColor = getLinearGradientColor(colorStop.color, nextColorStop.color, percentage / 100);

				  dColorPalette.push(interpolatedColor);
				}

				return dColorPalette;
			},
			huanOption(){
				return{
					toolbox: {
					    feature: {
					      saveAsImage: {}
					    }
					  },
					tooltip: {
					    trigger: 'item',
						formatter: '{b}: {c} ({d}%)', // 显示名称、值和百分比  
						position: 'auto'
					  },
					  
					  series: [
					    {
					      type: 'pie',
							label: {
							  show: false,
							  position: 'center',
							  formatter: '{b} '// 显示名称和值  \n{c}'
							},
							      emphasis: {
									
							        label: {
							          show: true,
							          fontSize: 20,
							          fontWeight: 'bold',
							        }
							      },
					      radius: ['40%', '70%'],
						  color: this.dynamicColorPalette,
					      data: this.chartData,						  
					    }
					  ]
				}
			},
			pieOption() {
				return{
					toolbox: {
					    feature: {
					      saveAsImage: {}
					    }
					  },
				tooltip: {
				    trigger: 'item',
					formatter: '{b}: {c} ({d}%)', // 显示名称、值和百分比
					position:'auto'
				  },
				  series: [
				    {
				      type: 'pie',
				      radius: '50%',
					  label:{
						  color: 'auto',
					  },
					  color: this.dynamicColorPalette,
				      data: this.chartData,
					  // selectedMode: 'single',
				      emphasis: {
				        itemStyle: {
				          shadowBlur: 10,
				          shadowOffsetX: 0,
				          shadowColor: 'rgba(0, 0, 0, 0.5)'
				        }
				      },
					  
				    }
				  ]
			}
		},
		   
				   
				   
				    
					
		},
		methods:{
			  setChartOptions() {
			  	this.barOption.xAxis.data=this.chartData.map(item => item.name)
			  	this.barOption.series[0].data=this.chartData.map(item => item.value)
				this.tiaoOption.yAxis.data=this.chartData.map(item => item.name)
				this.tiaoOption.series[0].data=this.chartData.map(item => item.value)
				this.lineOption.xAxis.data=this.chartData.map(item => item.name)
				this.lineOption.series[0].data=this.chartData.map(item => item.value)
			  },
			onSwiperChange(e) {
			    const previousSwiper = this.currentSwiper;
				  const currentSwiper = e.detail.current;
			
				  if (currentSwiper - previousSwiper === 1 || currentSwiper - previousSwiper === -1) {
					this.currentSwiper = currentSwiper;
					
				  }
				  if(currentSwiper - previousSwiper > 1)
				  {
					this.currentSwiper = previousSwiper+1;
				  }
				  if(currentSwiper - previousSwiper < -1)
				  {
					  this.currentSwiper = previousSwiper-1;
				  }
			  },
			
			  onBottomItemClick(index) {

			    this.currentSwiper=index
			  },
		}
	}
</script>
<style lang="less">
*{
    box-sizing: border-box;
}
.Block {
  border-radius: 20px;
  background: rgba(255, 255, 255, 0.8);
  box-shadow: 2px 2px 20px 0px rgba(136, 63, 143, 0.15);
  display: flex;
  flex-direction: column;
  width: 80vw;
  min-height: 40vh;
  justify-content: space-between;
  box-sizing: border-box;
  padding: 3% 5% 5% 3%;
  align-items: stretch;
}

.topBox {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  align-content: center;
  flex-direction: row;
  height: fit-content;
  width: 100%;

  .title {
    font-size: 20px;
    font-weight: 400;
    color: black;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }

  .type {
    font-size: 11pt;
    font-weight: 400;
    color: #bbbbc7;
    white-space: nowrap;
    display: inline-block;
    margin-left: 3%;
  }
}

.container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.middleBox {
  width: 100%;
  height: 100%;
  margin-top: 5%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.displayText {
  font-size: 24px;
  font-weight: bold;
}

.bottomBox {
  width: 100%;
  height: fit-content;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  align-content: center;
}

.bottomItem {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 16px;
}

.ellipse {
  border-radius: 999px;
  background: #efd6e8;
  padding: 2px 4px;
  margin-left: 4px;
}
.header {
  display: flex;
  justify-content: space-between;
  font-weight: bold;
  margin-bottom: 5px;
}

.row {
  display: flex;
  justify-content: space-between;
  margin-bottom: 5px;
}

.chartContainer {
	width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-content: center;
    align-items: center;
    flex-direction: column;
  .pie{
	  width: 100%;
	  height: 100%;
  }
}
.pppie{
	display: flex;
	flex-direction: column;  
	align-items: center;  
	justify-content: center;  
	height: 100%;
}

</style>
