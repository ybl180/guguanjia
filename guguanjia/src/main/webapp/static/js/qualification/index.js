new Vue({
    el: ".main-content",
    data: {
        params: {}
        ,
        pageInfo: ''
    },
    methods: {
        selectAll: function (pageNum, pageSize) {
            this.params.pageNum = pageNum;
            this.params.pageSize = pageSize;
            axios({
                url: "manager/qualification/toList",
                method: "post",
                data: this.params,
            }).then(response => {
                this.pageInfo = response.data;
            }).catch()
        },
        toUpdate:function(id,uploadUserOfficeId){
            axios({
                url:"manager/qualification/toUpdate",
                params: {id:id}
            }).then( response=>{
                    layer.obj=response.data;
                    layer.offerId=uploadUserOfficeId;
                    layer.open({
                        type:2,
                        area:["80%","80%"],
                        content:"manager/qualification/toUpdatePage",
                        end:()=>{
                            this.selectAll()
                        }
                    })
                }
            ).catch()
        }


    },
    created: function () {
        this.selectAll(1, 5);
    }
});