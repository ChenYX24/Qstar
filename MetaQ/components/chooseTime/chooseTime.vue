<template>  
    <view class="contentBox">  
      <view class="startTime" @tap="showStartTimePicker">  
        <view class="time">  
          开始时间: {{ startTime }}  
        </view>  
        <view class="icon">  
          >  
        </view>  
      </view>  
      <view class="endTime" @tap="showEndTimePicker">  
        <view class="time">  
          结束时间: {{ endTime }}  
        </view>  
        <view class="icon">  
          >  
        </view>  
      </view>  
    </view>  
  
    <view class="picker" v-if="showPicker">  
      <view class="mask"></view>  
      <view class="picker-content">  
        <view class="btns">  
          <view class="btn" @tap="cancel">取消</view>  
          <view class="btn" @tap="confirm">确定</view>  
        </view>  
        <picker-view class="picker-view" :value="value" @change="onChange">  
          <picker-view-column>  
            <view v-for="(item, index) in years" :key="index">{{ item }}</view>  
          </picker-view-column>  
          <picker-view-column>  
            <view v-for="(item, index) in months" :key="index">{{ item }}</view>  
          </picker-view-column>  
          <picker-view-column>  
            <view v-for="(item, index) in days" :key="index">{{ item }}</view>  
          </picker-view-column>  
          <picker-view-column>  
            <view v-for="(item, index) in hours" :key="index">{{ item }}</view>  
          </picker-view-column>  
          <picker-view-column>  
            <view v-for="(item, index) in minutes" :key="index">{{ item }}</view>  
          </picker-view-column>  
          <picker-view-column class="endBox" v-if="!isStartTime">  
            <view>无截止时间</view>  
            <view>选择截止时间</view>  
          </picker-view-column>  
        </picker-view>  
      </view>  
    </view>   
</template>  
  
<script>  
export default {  
  data() {  
    const now = new Date();  
    const currentYear = now.getFullYear();  
    const currentMonth = now.getMonth() + 1< 10 ? '0' + (now.getMonth() + 1) : (now.getMonth() + 1);  
    const currentDay = now.getDate()< 10 ? '0' + now.getDate(): now.getDate();  
    const currentHour = now.getHours()< 10 ? '0' + now.getHours() : now.getHours();  
    const currentMinute = now.getMinutes()< 10 ? '0' + now.getMinutes() : now.getMinutes(); 
    const startTime = `${currentYear}-${currentMonth}-${currentDay} ${currentHour}:${currentMinute}`;  
  
    return {  
      startTime,  
      endTime: '无截止时间',  
      showPicker: false,  
      isStartTime: true,  
      years: [],
      months: [],
      days: [],
      hours: [],
      minutes: [],
	  prevValue: [0, 0, 0, 0, 0, 0], 
      value: [0, 0, 0, 0, 0, 0]  
    };  
  }, 
  mounted() {  
    this.initPickerData();  
  },    
	emits: ['getTime'], 
  methods: {
	  emitTime() {  
		this.$emit('getTime', { startTime: this.startTime, endTime: this.endTime });  
	  },  

	  getDaysInMonth(year, month) {
	    return new Date(year, month, 0).getDate();  
	  }, 
    initPickerData() {  
      const now = new Date();  
      const currentYear = now.getFullYear();  
  
      let years = [];  
      for (let i = currentYear; i <= currentYear + 10; i++) {  
        years.push(i);  
      }  
  
      let months = [];  
      for (let i = 1; i <= 12; i++) {
		months.push(i < 10 ? '0' + i : i);
	  }
	  // 用选择的年份和月份动态更新天数数组
	  const selectedYear = this.years[this.value[0]];  
	  const selectedMonth = this.months[this.value[1]];  
	  const daysInMonth = this.getDaysInMonth(selectedYear, selectedMonth);
	  let days = [];  
	  for (let i = 1; i <= 31; i++) {  
		days.push(i < 10 ? '0' + i : i);  
	  }  

	  let hours = [];  
	  for (let i = 0; i < 24; i++) {  
		hours.push(i < 10 ? '0' + i : i);  
	  }  

	  let minutes = [];  
	  for (let i = 0; i < 60; i++) {  
		minutes.push(i < 10 ? '0' + i : i);  
	  }  

	  this.years = years;  
	  this.months = months;  
	  this.days = days;  
	  this.hours = hours;  
	  this.minutes = minutes;  
	},  
	showStartTimePicker() {  
	  this.updatePickerValue(this.startTime);
	  this.showPicker = true;  
	  this.isStartTime = true;  
	},  
	showEndTimePicker() {  
	  this.showPicker = true;  
	  this.isStartTime = false;
	  if (this.endTime !== '无截止时间') {
		this.updatePickerValue(this.endTime);  
	  } else {  
		this.updatePickerValue(this.startTime);
		this.value=[...this.value,0]
	  }  
	},  
	updatePickerValue(time) {  
	  if (time === '无截止时间') {  
		this.value = [0, 0, 0, 0, 0, 0];  
		return;  
	  }  
	  const [year, month, day, hour, minute] = time.split(/[- :]/).map(item => parseInt(item));  
	  const yearIndex = this.years.indexOf(year);  
	  const monthIndex = this.months.indexOf(month < 10 ? '0' + month : parseInt(month));  
	  const dayIndex = this.days.indexOf(day < 10 ? '0' + day : parseInt(day));  
	  const hourIndex = this.hours.indexOf(hour < 10 ? '0' + hour : parseInt(hour));  
	  const minuteIndex = this.minutes.indexOf(minute < 10 ? '0' + minute : parseInt(minute));  
	  const isEndTime = this.value[5];  
	  this.value = [yearIndex, monthIndex, dayIndex, hourIndex, minuteIndex,isEndTime]; 
	},
 
	cancel() {  
	  this.showPicker = false;  
	},  
	confirm() {  
	  if (!this.isStartTime && this.value[5] === 0) {  
		this.endTime = '无截止时间';
		  this.emitTime() 
	  } else {  
		let selectedTime = `${this.years[this.value[0]]}-${this.months[this.value[1]]}-${this.days[this.value[2]]} ${this.hours[this.value[3]]}:${this.minutes[this.value[4]]}`;  
		if (this.isStartTime) {  
		  let startTimeDate = new Date(selectedTime);  
		  let endTimeDate = this.endTime !== '无截止时间' ? new Date(this.endTime) : null; 
		  if (endTimeDate && startTimeDate.getTime() > endTimeDate.getTime()) {  
			this.endTime = '无截止时间';  
			this.emitTime();
		  }  
		  this.startTime = selectedTime;
			this.emitTime();
		} else {  
		  let startTimeDate = new Date(this.startTime);  
		  let endTimeDate = new Date(selectedTime);  
		  if (startTimeDate.getTime() < endTimeDate.getTime()) {  
			this.endTime = selectedTime;
			  this.emitTime();
		  } else {
			  this.value[5]=0
			uni.showToast({  
			  title: '结束时间不能比开始时间早',  
			  icon: 'none'
			});  
		  }  
		}  
	  }  
	  this.showPicker = false;  
	},  
	onChange(e) {  	       
	   this.prevValue = this.value;  
	  if (!this.isStartTime && e.detail.value[5] === 0&&this.value[5]===0) { 
		this.value = [...this.value, 0];  
	  }
	  else
	  {
		  this.value = e.detail.value;		   
		   // 判断年份或月份是否发生更改  
		   if (this.value[0] !== this.prevValue[0] || this.value[1] !== this.prevValue[1]) {  
		     // 更新天数数组  
		     const selectedYear = this.years[this.value[0]];  
		     const selectedMonth = this.months[this.value[1]];  
		     const daysInMonth = this.getDaysInMonth(selectedYear, selectedMonth);  
		       
		     let days = [];  
		     for (let i = 1; i <= daysInMonth; i++) {  
		       days.push(i < 10 ? '0' + i : i);  
		     }  
		       
		     this.days = days;  
		   }  
	  }
	}  
	},
	};
</script>
<style> 
	.page { 
		display: flex;
		flex-direction: column; 
		align-items: center; 
	} 
	.contentBox{
		border-radius: 20px;
		background: rgba(255, 255, 255, 0.8);
		box-shadow: 2px 2px 20px 0px rgba(136, 63, 143, 0.15);
		width: 80vw;
		height: 15vh;
		box-sizing: border-box;
		padding: 5%;
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
		.startTime,.endTime{
			width: 100%;
			display: flex;
			flex-direction: row;
			justify-content: space-between;
			align-items: center;
			background: rgba(255, 255, 255, 0.8);
			.time{
				font-size: 12pt;
				color: rgba(187, 187, 199, 1);
			}
			.icon{
				font-size: 18pt;
				color: #a5a5be;
			}
		}
		.endTime{ 
			border-width: 0 2px 2px 2px;
			border-style: solid;  
			border-color: #e1e1eb;  
		}
		.startTime{
			border-width: 2px 2px 2px 2px;
			border-style: solid;
			border-color: #e1e1eb;
		}
	}
	.mask { 
		position: fixed; 
		top: 0; 
		left: 0; 
		width: 100%; 
		height: 100%; 
		background-color: rgba(0, 0, 0, 0.3); 
	} 
	.picker{
		z-index: 9999;
	}
	.picker-content { 
		position: fixed; 
		bottom: 0; 
		left: 0; 
		width: 100%; 
		background-color: #fff; 
	} 
	.btns { 
		display: flex; 
		justify-content: space-between; 
		padding: 10px; 
	} 
	.btn { 
		color: #007aff; 
	} 
	.picker-view { 
		height: 200px; 
		    box-sizing: border-box;
		    padding: 0 2% 0 5%;
	}
	.endBox{
		font-size: 10pt;
		flex:1.5
	} 
</style>
