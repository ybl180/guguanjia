new Vue({
    el: "#main-container",
    data: {
        detail: ''
    },
    methods: {},
    created: function () {
        this.detail = parent.layer.obj;
    }
});