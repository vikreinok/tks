window.Diner = Backbone.Model.extend({
	
	urlRoot: "http://localhost:8080/catering-core/rest/diner/",
	
	initialize: function () {
		this.validators = {};

        this.validators.name = function (value) {
            return value.length >= 2 && value.length <= 20  ? {isValid: true} : {isValid: false, message: "You must enter an diner name from 2 to 15 characters"};
        };
        
        this.validators.description = function (value) {
            return value.length > 0 ? {isValid: true} : {isValid: false, message: "You must enter an diner description"};
        };
        
    },
    
    validateItem: function (key) {
        return (this.validators[key]) ? this.validators[key](this.get(key)) : {isValid: true};
    },
	
	validateAll: function () {

        var messages = {};

        for (var key in this.validators) {
            if(this.validators.hasOwnProperty(key)) {
                var check = this.validators[key](this.get(key));
                if (check.isValid === false) {
                    messages[key] = check.message;
                }
            }
        }

        return _.size(messages) > 0 ? {isValid: false, messages: messages} : {isValid: true};
    },
    
    defaults: {
        id: null,
        name: "",
        description: "",
        picture: "-1",
        modifyDate: "",
        created: ""
    }
    
});

window.DinerCollection = Backbone.Collection.extend({
	model: Diner,
	url: "/catering-core/rest/diner/",
    initialize: function(){
        console.log("Diner collection initialized");
    },

	findByName:function (key) {
		var url = '/catering-core/rest/diner/name/'+ key;
		console.log('findByName: ' + url);
		console.log('findByName: ' + key);
		var self = this;
	    $.ajax({
	        url:url,
	        dataType:"json",
	        success:function (data) {
	            console.log("search success: " + data.length);
	            self.reset(data);
	        }
	    });
	}
});