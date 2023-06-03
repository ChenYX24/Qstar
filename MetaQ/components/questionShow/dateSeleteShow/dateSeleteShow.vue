<template>
<view>
    <DatePicker @dayclick="test" v-model.range="range" mode="date" ref="rangeDate"/>
	<DatePicker @dayclick="test"  mode="date"/>

</view>
</template>

<script>
import {DatePicker} from 'v-calendar';
import 'v-calendar/style.css';

export default {
    data() {
        return {
			range:({
				start: "2022-1-23",
				  end: "2022-1-25"
			})
		};
    },
	components:{
		DatePicker
	},
    methods: {
		test(e){
			// this.$refs.rangeDate.range
			// console.log(this.range.start,'------',this.range.end)
			// console.log(e)
		}
    }
};
</script>

<style lang="less">

</style>
