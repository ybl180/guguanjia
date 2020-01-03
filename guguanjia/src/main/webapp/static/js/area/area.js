new Vue({
    el: "#main-container",
    data: {},
    methods: {
        download: function () {
            location.href = 'manager/area/download';
        },
        upload: function (event) {
            console.log(event.target.files[0]);
            let formData = new FormData;//构建表单对象
            formData.append("upFile", event.target.files[0]);//upFile需要与后台接收MultipartFile的名字一致
            axios({
                url: "manager/area/upload",
                data: formData,
                method: "post",
                headers:{//设置请求头
                    'Content-Type':'Multipart/form-data'//设置请求题格式
                }
            }).then(response => {
                layer.msg(response.data.msg);
            }).catch(error => {
                layer.msg(error.message);
            });
        }
    }
});