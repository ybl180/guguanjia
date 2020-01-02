new Vue({
    el: "#first",
    data: {
        detail: '',
        total: '',
        transportTime: '',
        recipientTime: ''
    },
    methods: {},
    created: function () {
        this.detail = parent.layer.obj;
        let num = 0;
        for (let i = 0; i < this.detail.details.length; i++) {
            num += parseFloat(this.detail.details[i].weight)
        }
        this.total = num.toFixed(2);//总计
        this.transportTime = this.detail.transportUser.createDate.split(" ")[0].split("-");//运输时间处理
        this.recipientTime = this.detail.recipientUser.createDate.split(" ")[0].split("-");//处理人时间处理
    }
});