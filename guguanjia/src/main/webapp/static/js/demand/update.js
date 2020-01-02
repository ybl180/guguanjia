new Vue({
    el: "#main-container",
    data: {
        demand: {}
    },
    methods: {
        doUpdate: function () {
            axios({
                url: "manager/demand/doUpdate",
                method: "post",
                data: this.demand
            }).then(response => {
                if (response.data.success) {
                    this.close1();
                    parent.layer.msg(response.date.msg)
                }
                layer.msg(response.date.msg)
            }).catch(error=>{

            })
        },
        close1: function () {
            let index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }
    },
    created: function () {
        this.demand = parent.layer.obj
    }
})