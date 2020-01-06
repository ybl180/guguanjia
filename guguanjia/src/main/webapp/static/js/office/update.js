new Vue({
    el: "#main-container",
    data: {
        office: {},
        wasteTypes: [],
        wastes: []
    },
    methods: {
        doUpdate: function () {
            axios({
                url:'manager/office/doUpdate',
                method:'post',
                data:this.office
            }).then(response => {
                layer.msg(response.data.msg);

            }).catch(function (error) {
                layer.msg(error.message);
            })
        },
        createdWasteTypes: function () {
            axios({
                url: "manager/office/selectWasteType"
            }).then(response => {
                this.wasteTypes = response.data;
                console.log(this.wasteTypes)
            }).catch()
        },

        createWastes: function (e, param) {
            //e 事件源；param 触发事件的元素选项
            axios({
                url: "manager/office/selectWaste",
                params: param
            }).then(response => {
                this.wastes = response.data;

                //遍历所有wasteTypes  得到  wasteTypeCode
                for (let i = 0; i < this.wasteTypes.length; i++) {
                    if (this.wasteTypes[i].id == param.selected) {
                        this.wasteTypeCode = this.wasteTypes[i].code;
                    }
                }

            }).catch()
        },

        selectWaste: function (e, param) {
            //从this.wastes中获取waste，放入office.wastes
            for (let i = 0; i < this.wastes.length; i++) {
                if (this.wastes[i].id == param.selected) {
                    //判断在office.wastes中是否存在，不存在才添加
                    let flag = false;//不存在
                    for (let j = 0; j < this.office.wastes.length; j++) {
                        if (this.office.wastes[j].id == this.wastes[i].id) {
                            flag = true;//存在
                            break;
                        }
                    }
                    if (!flag) {//不存在则放入集合中
                        this.wastes[i].wasteTypeCode = this.wasteTypeCode;//给wasteTypeCode赋值
                        this.office.wastes.push(this.wastes[i]);
                    }
                }
            }
        },

    },
    created: function () {
        this.office = parent.layer.obj;
        this.createdWasteTypes()
    },
    mounted: function () {
        $("#chosen-select").chosen({width: '100%'});//初始化机构类型
        $("#wasteType").chosen({width: '100%'});
        $("#waste").chosen({width: '100%'});

        //动态绑定wasteType的change事件
        //不能vue@change绑定，因为页面上点击到元素是chosen动态生成的元素，所以得绑定到chosen的元素身上
        $("#wasteType").on("change", this.createWastes);
        $("#waste").on("change", this.selectWaste);
    },
    updated: function () {
        $("#wasteType").trigger("chosen:updated");//更新option后，通知chosen重新生成新的选项框
        $("#waste").trigger("chosen:updated");
    }
})
;