<template>
	<view class="dateSeleteAnswer">
			<view class="box">
				<view class="inbox">
					<view class="title">
						{{num}}.{{content.question}}<text>[日期]</text>
					</view>
					<view class="">
						{{range.start.toLocaleDateString()}}-{{range.end.toLocaleDateString()}}
					</view>
					<DatePicker @dayclick="test" v-model.range="range" />
				</view>	
		</view>
			
		{{date.toLocaleDateString()}}
		<view class="singleDate">
			<DatePicker @dayclick="test" v-model="date" mode="date"/>
		</view>
	</view>
	
</template>

<script>
import {DatePicker} from 'v-calendar';
import 'v-calendar/style.css';

export default {
    data() {
        return {
			range:({
				start: new Date(2023, 0, 6),
				  end: new Date(2023, 0, 10)
			}),
			date:(new Date(2023, 0, 6)),
			masks:({
				modelValue: 'YYYY-MM-DD',
			})
		};
    },
	components:{
		DatePicker
	},
	props:{
		num:{
			type:String,
			default:'1'
		},
		content:{
			type:Object,
			default:{
				question:"5555",
				type:'blank',
				choice:['1','2','3','4'],
			}
		}
	},
    methods: {
		
    }
};
</script>

<style lang="less">


</style>
