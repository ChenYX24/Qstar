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
      <swiper-item class="chart" v-for="(item, index) in SData" :key="index">
	          <view v-if="index === 0">
	            <!-- 单选题列表 -->
	            <view class="header">
	              <text class="option">选项</text>
	              <text class="ratio">比例</text>
	              <text class="count">数量</text>
	            </view>
	            <view v-for="(data, dataIndex) in chartData" :key="dataIndex" class="row">
	              <text class="option">{{ data.name }}</text>
	              <text class="ratio">{{ data.ratio }}</text>
	              <text class="count">{{ data.value }}</text>
	            </view>
	          </view>
	          <view v-if="index === 1" class="chartContainer">
	            <!-- 饼状图 -->
				<echarts   
				ref="chartContainer"
				:option="pieOption"
				@click="onChartClick"
				class="pie"
				>
				</echarts>
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
		},
		
		data() {
			return {
				pushText:"已发布",
				CType:'',
				SData:["列表","饼状","环状","柱状","条形","折线"],
				displayText: "列表",
				currentSwiper: 0,
				chartData: [
					{ name: '选项1', ratio: '50%', value: '10' },
					{ name: '选项2', ratio: '25%', value: '5' },
					{ name: '选项3', ratio: '25%', value: '5' },
				],
				pieData:[
				  { value: 1048, name: 'Search Engine' },
				  { value: 735, name: 'Direct' },
				  { value: 580, name: 'Email' },
				  { value: 484, name: 'Union Ads' },
				  { value: 300, name: 'Video Ads' },
				  { value: 300, name: 'Vdeo' },
				  { value: 300, name: 'Vieo' },
				  { value: 300, name: 'Vido' },
				  { value: 300, name: 'Vide' },
				]


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
				  { color: { r: 74, g: 200, b: 185, a: 1 }, position: 4.16 },
				  { color: { r: 46, g: 205, b: 217, a: 1 }, position: 9.26 },
				  { color: { r: 164, g: 239, b: 245, a: 1 }, position: 25.93 },
				  { color: { r: 110, g: 222, b: 230, a: 1 }, position: 39.81 },
				  { color: { r: 220, g: 220, b: 220, a: 1 }, position: 56.27 },
				  { color: { r: 189, g: 158, b: 255, a: 1 }, position: 80.28 },
				  { color: { r: 129, g: 99, b: 199, a: 1 }, position: 87.5 },
				  { color: { r: 90, g: 36, b: 240, a: 1 }, position: 94.91 }
				];

				const dataLength = this.pieData.length;

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

				console.log(dColorPalette);
				return dColorPalette;
			},
			
			pieOption() {
				return{
				tooltip: {
				    trigger: 'item'
				  },
				  series: [
				    {
				      name: 'Access From',
				      type: 'pie',
				      radius: '50%',
				      selectedMode: 'single',
					  color: this.dynamicColorPalette,
				      data: this.pieData,
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
		}
			
		},
		methods:{
			onSwiperChange(e) {
			    const previousSwiper = this.currentSwiper;
				  const currentSwiper = e.detail.current;
			
				  if (currentSwiper - previousSwiper === 1 || currentSwiper - previousSwiper === -1) {
					this.currentSwiper = currentSwiper;
					console.log(this.currentSwiper)
					
				  }
				  if(currentSwiper - previousSwiper > 1)
				  {
					this.currentSwiper = previousSwiper+1;
					console.log(this.currentSwiper)
				  }
				  if(currentSwiper - previousSwiper < -1)
				  {
					  this.currentSwiper = previousSwiper-1;
					  console.log(this.currentSwiper)
				  }
			  },
			
			  onBottomItemClick(index) {
				  console.log(index)
			    this.currentSwiper=index
			  },
				onChartClick(params) {
				  if (params.componentType === 'series' && params.seriesType === 'pie') {
					const clickedName = params.name;
					const clickedValue = params.value;

					// Display the clicked name and value in the console or use them in your desired way
					console.log('Clicked Name:', clickedName);
					console.log('Clicked Value:', clickedValue);

					// Update the pieOption to highlight the clicked slice
					const newData = this.pieOption.series[0].data.map((item) => {
					  if (item.name === clickedName) {
						return { ...item, emphasis: { scale: true, focus: 'series' } };
					  } else {
						return { ...item, emphasis: { scale: false, focus: 'series' } };
					  }
					});
					this.pieOption.series[0].data = newData;
					this.$refs.chartContainer.refresh(); // Refresh the chart to apply the changes
				  }
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
  min-height: 30vh;
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

</style>
