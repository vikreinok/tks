window.LoginView = Backbone.View.extend({

    initialize: function () {
    	this.render();
    	this.afterRender();
    },

    render: function () {
        $(this.el).html(this.template(this.model.toJSON()));
        return this;
    },
    
    afterRender : function() {
		setTimeout(function() {
			$("#username").focus();
		}, 1);
	},
    
    events: {
        "change"         : "change",
        "click .save"    : "beforeLogin",
        "keydown :input" : "enter",
    },
  
    enter: function(e) {
    	if(e.keyCode == 13)
    		this.beforeLogin();
    },
    
	change: function (event) {
		    
	        // Remove any existing alert message
	        utils.hideAlert();
	
	        // Apply the change to the model
	        var target = event.target;
	        var change = {};
	        
        	change[target.name] = target.value;
        	this.model.set(change);
        	
	        // Run validation rule (if any) on changed item
	        var check = this.model.validateItem(target.id);
	        
	        if (check.isValid === false) {
	            utils.addValidationError(target.id, check.message);
	        } else {
	            utils.removeValidationError(target.id, this.model);
	        }
	    	
    },
    
    beforeLogin: function () {
    	
        var check = this.model.validateAll();        
        if (check.isValid === false) {
            utils.displayValidationErrors(check.messages);
            return false;
        }
        
    	this.login();
    },
    
    login: function () {
    	
    	var self = this;
        this.model.save(null, {
            success: function (model) {
          	  	console.log(model.attributes);
          	  	
                self.render();
                $('#loadingModal').modal('hide');
                
                utils.showAlert('Success!', 'Login successful', 'alert-success');
                
        		$('.navbar-nav li.logout').show();
        		$('.navbar-nav li.login').hide();
        		
         		$.cookie("authenticated", true);

                utils.redirectTimer(1000, '/diners');
            },
            error: function () {
                utils.showAlert('Error', 'An error occurred while trying to login', 'alert-danger');
            }            
        });
    	
    }
    
    


});