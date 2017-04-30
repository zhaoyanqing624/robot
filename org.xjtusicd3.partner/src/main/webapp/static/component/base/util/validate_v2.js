
/**
 * ��֤��
 */
 var validateCallback = {}
 W={};
(function($, undefined) {
W.ValidaterClass = function() {
	var ascii = /[^\x00-\xff]/g;

	this.validateRules = {
        'require-word': {
            //��ĸ
            type: 'regex',
            extract: 'value',
            regex: /^[^._$]+$/g,
            errorHint: '��ʽ����ȷ���������".","_"��"$"������ַ�'
        },
        'require-letter': {
            //��ĸ
            type: 'regex',
            extract: 'value',
            regex: /^[a-zA-Z0-9_]+$/g,
            errorHint: '��ʽ����ȷ����������ĸ�����֡��»���'
        },
		'require-int': {
            //����
            type: 'regex',
            extract: 'value',
            regex: /^-?\d+$/g,
            errorHint: '��ʽ����ȷ������������'
        },
        'require-positive-int': {
            //������
            type: 'regex',
            extract: 'value',
            regex: /^[1-9][0-9]*$/g,
            errorHint: '��ʽ����ȷ��������������'
        },
        'require-nonnegative': {
            /*�Ǹ���*/
            type: 'regex',
            extract: 'value',
            regex: /^\d+$/g,
            errorHint: '������Ǹ�����'
        },
        'require-float': {
            type: 'regex',
            extract: 'value',
            regex: /^\d{1,5}(\.\d{1,2})?$/g,
            errorHint: '��ʽ����ȷ��������\'3.14\'��\'5\'����������'
        },
        'require-float-three': {
            type: 'regex',
            extract: 'value',
            regex: /^\d{1,5}(\.\d{1,3})?$/g,
            errorHint: '��ʽ����ȷ��������\'3.147\'��\'5\'����������'
        },
        'require-float-one': {
            type: 'regex',
            extract: 'value',
            regex: /^\d{1,5}(\.\d{0,1})?$/g,
            errorHint: '��ʽ����ȷ��������\'3.147\'��\'5\'����������'
        },
        'require-price': {
            type: 'regex',
            extract: 'value',
            regex: /^\d+(\.\d{0,2})?$/g,
            errorHint: '�۸���ȷ��������0-999999999֮��ļ۸�'
        },
        'require-date': {
            type: 'regex',
            extract: 'value',
            regex: /^[0-9]{4}-[0-9]{2}-[0-9]{2}$/g,
            errorHint: '�����ʽΪ2013-01-01������'
        },
        'require-percent': {
            type: 'regex',
            extract: 'value',
            regex: /^([1-9]|[1-9]\d|100)$/g,
            errorHint: '��ʽ����ȷ��������1-100������'
        },
		'require-tel-phone': {
			type: 'regex',
			extract: 'value',
			regex: /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/g,
			errorHint: '����ĵ绰�����д������ź͵绰����֮������-�ָ�'
		},
		'require-email': {
			type: 'regex',
			extract: 'value',
			regex: /^[a-zA-Z0-9]+([._\-]*[a-zA-Z0-9]+)*@([a-zA-Z0-9]+([._\-][a-zA-Z0-9]+))+$/,
			errorHint: '�����ʽ����'
		},
        'require-mobile-phone': {
            type: 'regex',
            extract: 'value',
            regex: /^(13[0-9]|15[012356789]|17[136780]|18[0-9]|14[57])[0-9]{8}$/g,
            errorHint: '�ֻ��Ÿ�ʽ����'
        },
        'require-password': {
            type: 'regex',
            extract: 'value',
            regex: /^[\w~`!@#$%&*()_+<>?:"'+*\/\-\^\\\][{}]{6,16}$/,
            errorHint: '������6-16λ���룬���ִ�Сд������ʹ�ÿո�'
        },
        'require-nick': {
            type: 'regex',
            extract: 'value',
            regex:/^[\w\u4E00-\u9FA5]{2,18}$/,
            errorHint: '�ǳ�2-18λ��Ӣ�ġ����ּ��»��ߣ�'
        },

		'require-notempty': {
			type: 'function',
			extract: 'element',
			check: function(element) {
				var trimedValue = $.trim(element.val());

				if (trimedValue.length == 0) {
					this.errorHint = '���ݲ���Ϊ��';
					return false;
				} else {
					return true;
				}
			},
			errorHint: ''
		},
        'require-element-exist': {
            type: 'function',
            extract: 'element',
            check: function(element) {
                var selector = element.data('selector');
                if (element.find(selector).length > 0) {
                    return true;
                } else {
                    return false;
                }
            }
        },
        'require-string': {
            type: 'function',
            extract: 'element',
            check: function(element) {
                var trimedValue = $.trim(element.val());

                // ֧��contenteditable jz
                if(element.attr('contenteditable') == 'true'){
                    trimedValue = element.attr('value');
                }
                var minLength = element.data('minlength') || element.data('min-length')|| 1;
                var maxLength = element.data('maxlength') || element.data('max-length') || 9999999;
                var actualLength = trimedValue.length;
                if (actualLength < minLength || actualLength > maxLength) {
                    if (maxLength == 9999999) {
                        this.errorHint = '����ֵ���ȱ�����ڵ���'+minLength;
                    } else {
                        this.errorHint = '�����볤����'+minLength+'��'+maxLength+'֮����ַ���';
                    }
                    return false;
                } else {
                    return true;
                }
            },
            errorHint: ''
        },
        'require-select': {
            type: 'function',
            extract: 'value',
            check: function(value) {
                var trimedValue = $.trim(value);
                if (trimedValue.length == 0 || trimedValue === "-1") {
                    return false;
                } else {
                    return true;
                }
            },
            errorHint: '��ѡ��һ��ѡ��'
        },
		'require-select-positive-option': {
			type: 'function',
			extract: 'value',
			check: function(value) {
				var value = parseInt(value);
				if (value < 0) {
					return false;
				} else {
					return true;
				}
			},
			errorHint: '��ѡ��һ��ѡ��'
		},
        'require-custom-function': {
            type: 'function',
            extract: 'element',
            check: function(element) {
            	var funcName = element.attr('data-validate-function');
            	var result = window[funcName](element);
            	this.errorHint = result['errorHint'];
                if (!result.isValidate && !this.errorHint) {
                    this.errorHint = '����data-validate-function�����з���errorHint'
                }
            	return result.isValidate;
            }
        },
        'require-file': {
            type: 'function',
            extract: 'value',
            check: function(value) {
                if (value.length == 0) {
                    return false;
                } else {
                    return true;
                }
            },
            errorHint: '��ѡ��һ���ļ�'
        },
        'require-length': {
            type: 'function',
            extract: 'element',
            check: function(element) {
                var trimedValue = $.trim(element.val());

                // ֧��contenteditable jz
                if(element.attr('contenteditable') == 'true'){
                    trimedValue = element.attr('value');
                }
                var strLength = parseInt(element.attr('strlength')) || parseInt(element.attr('str-length'));
                var actualLength = trimedValue.length;
                if (actualLength !== strLength) {
                    this.errorHint = '�����볤��Ϊ'+strLength+'���ַ���';
                    return false;
                } else {
                    return true;
                }
            },
            errorHint: ''
        }
	};

	this.getRule = function(type) {
		return this.validateRules[type];
	}
};

W.Validater = new W.ValidaterClass();


//��֤���
W.validate = function(el, checkDynamicElement) {
    var elements = [];
    if (el) {
        if(!el.attr('data-validate')) {
            elements.push(el.find('[data-validate]'));
        } else {
            elements.push(el);
        }
    } else {
        elements.push($('[data-validate]'));
    }

    var hasError = false;
    var elementCount = elements.length;
    var errorHint = null;
    for (var i = 0; i < elementCount; ++i) {
        var subElements = elements[i];
        subElements.each(function() {
            var $el = $(this);
            if (!$el.is(":visible") || $el.css('visibility') == 'hidden') {
                if ($el.data('forceValidate')) {
                    //we don't break even the $el is invisible if use force-validate
                } else {
                    W.validate.toggleErrorHint($el, true);
                    return;
                }
            }

            var value = $el.val();
            var validateTypeStr = $el.attr('data-validate');
            if (!validateTypeStr) {
                return;
            }

            var validateTypes = validateTypeStr.split(',,');
            var validateCount = validateTypes.length;
            for (var j = 0; j < validateCount; ++j) {
                var validateType = $.trim(validateTypes[j]);
                if ('norequire' == validateType && value.length == 0){
                    //hasError = false;
                    W.validate.toggleErrorHint($el, true);
                    break;
                }
                //validateType��������������ʽ: "require-notempty::abcd,, require-func1(a,b)::xyz"
                var items = validateType.split('::');
                var validateRule = null;
                var validateArgs = null;
                var validateErrorHint = null;
                if (items.length === 2) {
                    validateRule = items[0];
                    validateErrorHint = items[1];
                } else {
                    validateRule = items[0];
                }
                //�Ƿ��в���
                var pos = validateRule.indexOf('(');
                if (pos !== -1) {
                    items = validateRule.split('(');
                    validateRule = items[0];
                    validateArgs = items[1].substring(0, items[1].length-1).split(',');
                }

                //ִ����֤
                var validater = W.Validater.getRule(validateRule);
                if (!validater) {
                    continue;
                }
                if (validater.type === 'function') {
                    var target = value;
                    var $targetEl = $el;
                    if (validater.extract == 'element') {
                        target = $el;
                    }

                    if (!validater.check.call(validater, target, validateArgs)) {
                        hasError = true;
                        errorHint = validateErrorHint ? validateErrorHint : validater.errorHint;
                        W.validate.toggleErrorHint($targetEl, false, errorHint);
                        return true;


                        //end �����У��
                    } else {
                        //start �����У��

                        var callbackStr = $el.attr('data-callback');

                        if(callbackStr&&validateCallback&&validateCallback[callbackStr]){
                            var v = $el.val()
                            validateCallback['errorHint'] = '';
                            validateCallback[callbackStr](v)
                            if(validateCallback.rel){
                                W.validate.toggleErrorHint($targetEl, true);
                                hasError = false;
                            }else{
                                hasError = true;
                                W.validate.toggleErrorHint($targetEl, false, validateCallback.errorHint,true);
                                 return true;
                            }
                        }
                        else{
                            hasError = false;
                            W.validate.toggleErrorHint($targetEl, true);
                        }

                        
                    }
                } else if (validater.type === 'regex') {
                    var $targetEl = $el;
                    if (!value.match(validater.regex)) {
                        hasError = true;
                        errorHint = validateErrorHint ? validateErrorHint : validater.errorHint;
                        W.validate.toggleErrorHint($targetEl, false, errorHint);
                        return true;
                    } else {
                        //start �����У��
                        var callbackStr = $el.attr('data-callback');

                        if(callbackStr&&validateCallback&&validateCallback[callbackStr]){
                            var v = $el.val()
                            validateCallback['errorHint'] = '';
                            validateCallback[callbackStr](v)
                            if(validateCallback.rel){
                                W.validate.toggleErrorHint($targetEl, true);
                                hasError = false;
                            }else{
                                hasError = true;
                                W.validate.toggleErrorHint($targetEl, false, validateCallback.errorHint,true);
                                 return true;
                            }
                        }
                        else{
                            hasError = false;
                            W.validate.toggleErrorHint($targetEl, true);
                        }

                        if(hasError){
                            return false;
                        }
                        //end �����У��

                    }
                }
            }
        });
    }

    return !hasError;
}
W.validate.toggleErrorHint = function(el, isValidate, hint, notUseDomHint) {
    if (isValidate) {
    } else {
    }
    if (!el) {
        return;
    }
    var $errorHint = el.siblings('.errorHint');
    if ($errorHint.length == 0) {
        var parent = el.parent();
        $errorHint = parent.find('.errorHint');
    }
    if ($errorHint.length == 0) {
        $errorHint = el.parents('.xa-errorHintContainer').find('.errorHint');
    }
    if ($errorHint.length == 0) {
        $errorHint = el.parents('.x-errorHintContainer').find('.errorHint');
    }
    if ($errorHint.length == 0) {
        var targetSelector = el.data('errorHintTarget');
        if (targetSelector) {
            $errorHint = $(targetSelector);
        }
    }
    if ($errorHint.length > 0) {
        //��ʽ1��Ѱ����elͬ����errorHint����
        if (isValidate) {
            $errorHint.html('');
        } else {
            var elementHint = $errorHint.data('errorHint');
            if(notUseDomHint){
            }else{
                if (elementHint) {
                    hint = elementHint;
                }

            }
            $errorHint.html(hint).show();
        }
    }
    // ֧��contenteditable jz
    if(!isValidate && el.attr('errorhint-value') != undefined){
        el.attr('errorhint-value', hint)
    }

    //��ʾerror hint��ʾ
    var $container = el.parent();
    var isInputEl = (el.get(0).tagName.toLowerCase() === 'input');
    if (isInputEl) {
        //$container.addClass('has-feedback');
        //$container.find('.xa-feedback').remove();
        if (isValidate) {
            $container.removeClass('has-error');
            //$('<span class="xa-feedback glyphicon glyphicon-ok form-control-feedback"></span>').insertAfter(el);
        } else {
            $container.addClass('has-error');
            //$('<span class="xa-feedback glyphicon glyphicon-remove form-control-feedback"></span>').insertAfter(el);
        }
    }
}

$(document).delegate('input[data-validate]', 'blur', function(event) {

        W.validate($(event.currentTarget).parent());
});

})(jQuery);

