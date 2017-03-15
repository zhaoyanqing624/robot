(function(b) {
	b.html5 = {};
	b.html5.version = "6.12.0";
	var e = b.utils.css;
	var a = ".jwplayer ";
	var d = [ a, "div", "span", "a", "img", "ul", "li", "video" ]
			.join(", " + a);
	e(d + ", .jwclick", {
		margin : 0,
		padding : 0,
		border : 0,
		color : "#000000",
		"font-size" : "100%",
		font : "inherit",
		"vertical-align" : "baseline",
		"background-color" : "transparent",
		"text-align" : "left",
		direction : "ltr",
		"line-height" : 20,
		"-webkit-tap-highlight-color" : "rgba(255, 255, 255, 0)"
	});
	e(a + "," + a + "*", {
		"box-sizing" : "content-box"
	});
	e(a + "* button," + a + "* input," + a + "* select," + a + "* textarea", {
		"box-sizing" : "border-box"
	});
	e(a + "ul", {
		"list-style" : "none"
	});
	e(".jwplayer .jwcontrols", {
		"pointer-events" : "none"
	});
	e(".jwplayer.jw-user-inactive .jwcontrols", {
		"pointer-events" : "all"
	});
	var c = [ ".jwplayer .jwcontrols .jwdockbuttons",
			".jwplayer .jwcontrols .jwcontrolbar",
			".jwplayer .jwcontrols .jwskip",
			".jwplayer .jwcontrols .jwdisplayIcon",
			".jwplayer .jwcontrols .jwpreview", ".jwplayer .jwcontrols .jwlogo" ];
	e(c.join(", "), {
		"pointer-events" : "all"
	})
})(jwplayer);
(function(a) {
	var b = document;
	a.parseDimension = function(c) {
		if (typeof c === "string") {
			if (c === "") {
				return 0
			} else {
				if (c.lastIndexOf("%") > -1) {
					return c
				}
			}
			return parseInt(c.replace("px", ""), 10)
		}
		return c
	};
	a.timeFormat = function(e) {
		if (e > 0) {
			var d = Math.floor(e / 3600), f = Math.floor((e - d * 3600) / 60), c = Math
					.floor(e % 60);
			return (d ? d + ":" : "") + (f < 10 ? "0" : "") + f + ":"
					+ (c < 10 ? "0" : "") + c
		} else {
			return "00:00"
		}
	};
	a.bounds = function(d) {
		var g = {
			left : 0,
			right : 0,
			width : 0,
			height : 0,
			top : 0,
			bottom : 0
		};
		if (!d || !b.body.contains(d)) {
			return g
		}
		if (d.getBoundingClientRect) {
			var f = d.getBoundingClientRect(d), c = window.pageYOffset, e = window.pageXOffset;
			if (!f.width && !f.height && !f.left && !f.top) {
				return g
			}
			g.left = f.left + e;
			g.right = f.right + e;
			g.top = f.top + c;
			g.bottom = f.bottom + c;
			g.width = f.right - f.left;
			g.height = f.bottom - f.top
		} else {
			g.width = d.offsetWidth | 0;
			g.height = d.offsetHeight | 0;
			do {
				g.left += d.offsetLeft | 0;
				g.top += d.offsetTop | 0
			} while (d = d.offsetParent);
			g.right = g.left + g.width;
			g.bottom = g.top + g.height
		}
		return g
	};
	a.empty = function(c) {
		if (!c) {
			return
		}
		while (c.childElementCount > 0) {
			c.removeChild(c.children[0])
		}
	}
})(jwplayer.utils);
(function(a) {
	var b = a.stretching = {
		NONE : "none",
		FILL : "fill",
		UNIFORM : "uniform",
		EXACTFIT : "exactfit"
	};
	a.scale = function(e, d, c, g, h) {
		var f = "";
		d = d || 1;
		c = c || 1;
		g = g | 0;
		h = h | 0;
		if (d !== 1 || c !== 1) {
			f = "scale(" + d + ", " + c + ")"
		}
		if (g || h) {
			if (f) {
				f += " "
			}
			f = "translate(" + g + "px, " + h + "px)"
		}
		a.transform(e, f)
	};
	a.stretch = function(j, o, n, h, l, i) {
		if (!o) {
			return false
		}
		if (!n || !h || !l || !i) {
			return false
		}
		j = j || b.UNIFORM;
		var d = Math.ceil(n / 2) * 2 / l, g = Math.ceil(h / 2) * 2 / i, e = (o.tagName
				.toLowerCase() === "video"), f = false, k = "jw"
				+ j.toLowerCase();
		switch (j.toLowerCase()) {
		case b.FILL:
			if (d > g) {
				g = d
			} else {
				d = g
			}
			f = true;
			break;
		case b.NONE:
			d = g = 1;
		case b.EXACTFIT:
			f = true;
			break;
		case b.UNIFORM:
		default:
			if (d > g) {
				if (l * g / n > 0.95) {
					f = true;
					k = "jwexactfit"
				} else {
					l = l * g;
					i = i * g
				}
			} else {
				if (i * d / h > 0.95) {
					f = true;
					k = "jwexactfit"
				} else {
					l = l * d;
					i = i * d
				}
			}
			if (f) {
				d = Math.ceil(n / 2) * 2 / l;
				g = Math.ceil(h / 2) * 2 / i
			}
		}
		if (e) {
			var c = {
				left : "",
				right : "",
				width : "",
				height : ""
			};
			if (f) {
				if (n < l) {
					c.left = c.right = Math.ceil((n - l) / 2)
				}
				if (h < i) {
					c.top = c.bottom = Math.ceil((h - i) / 2)
				}
				c.width = l;
				c.height = i - 40;
				a.scale(o, d, g, 0, 0)
			} else {
				f = false;
				a.transform(o)
			}
			a.css.style(o, c)
		} else {
			o.className = o.className.replace(
					/\s*jw(none|exactfit|uniform|fill)/g, "")
					+ " " + k
		}
		return f
	}
})(jwplayer.utils);
(function(a) {
	a.dfxp = function() {
		var c = jwplayer.utils.seconds;
		this.parse = function(h) {
			var e = [ {
				begin : 0,
				text : ""
			} ];
			h = h.replace(/^\s+/, "").replace(/\s+$/, "");
			var g = h.split("</p>");
			var k = h.split("</tt:p>");
			var j = [];
			var d;
			for (d = 0; d < g.length; d++) {
				if (g[d].indexOf("<p") >= 0) {
					g[d] = g[d].substr(g[d].indexOf("<p") + 2).replace(/^\s+/,
							"").replace(/\s+$/, "");
					j.push(g[d])
				}
			}
			for (d = 0; d < k.length; d++) {
				if (k[d].indexOf("<tt:p") >= 0) {
					k[d] = k[d].substr(k[d].indexOf("<tt:p") + 5).replace(
							/^\s+/, "").replace(/\s+$/, "");
					j.push(k[d])
				}
			}
			g = j;
			for (d = 0; d < g.length; d++) {
				var f = b(g[d]);
				if (f.text) {
					e.push(f);
					if (f.end) {
						e.push({
							begin : f.end,
							text : ""
						});
						delete f.end
					}
				}
			}
			if (e.length > 1) {
				return e
			} else {
				throw {
					message : "Invalid DFXP file:"
				}
			}
		};
		function b(g) {
			var f = {};
			try {
				var d = g.indexOf('begin="');
				g = g.substr(d + 7);
				d = g.indexOf('" end="');
				f.begin = c(g.substr(0, d));
				g = g.substr(d + 7);
				d = g.indexOf('"');
				f.end = c(g.substr(0, d));
				d = g.indexOf('">');
				g = g.substr(d + 2);
				f.text = g
			} catch (e) {
			}
			return f
		}
	}
})(jwplayer.parsers);
(function(a) {
	a.srt = function() {
		var c = jwplayer.utils, d = c.seconds;
		this.parse = function(j, k) {
			var f = k ? [] : [ {
				begin : 0,
				text : ""
			} ];
			j = c.trim(j);
			var h = j.split("\r\n\r\n");
			if (h.length === 1) {
				h = j.split("\n\n")
			}
			for (var e = 0; e < h.length; e++) {
				if (h[e] === "WEBVTT") {
					continue
				}
				var g = b(h[e]);
				if (g.text) {
					f.push(g);
					if (g.end && !k) {
						f.push({
							begin : g.end,
							text : ""
						});
						delete g.end
					}
				}
			}
			if (f.length > 1) {
				return f
			} else {
				throw {
					message : "Invalid SRT file"
				}
			}
		};
		function b(k) {
			var j = {};
			var l = k.split("\r\n");
			if (l.length === 1) {
				l = k.split("\n")
			}
			try {
				var e = 1;
				if (l[0].indexOf(" --> ") > 0) {
					e = 0
				}
				var g = l[e].indexOf(" --> ");
				if (g > 0) {
					j.begin = d(l[e].substr(0, g));
					j.end = d(l[e].substr(g + 5))
				}
				if (l[e + 1]) {
					j.text = l[e + 1];
					for (var h = e + 2; h < l.length; h++) {
						j.text += "<br/>" + l[h]
					}
				}
			} catch (f) {
			}
			return j
		}
	}
})(jwplayer.parsers);
(function(b) {
	var e = b.utils.noop, a = b._, d = b.events, c = a.constant(false);
	var f = {
		supports : c,
		play : e,
		load : e,
		stop : e,
		volume : e,
		mute : e,
		seek : e,
		seekDrag : e,
		resize : e,
		remove : e,
		destroy : e,
		setVisibility : e,
		setFullscreen : c,
		getFullscreen : e,
		getContainer : e,
		setContainer : c,
		isAudioFile : c,
		supportsFullscreen : c,
		getQualityLevels : e,
		getCurrentQuality : e,
		setCurrentQuality : e,
		getAudioTracks : e,
		getCurrentAudioTrack : e,
		setCurrentAudioTrack : e,
		checkComplete : e,
		setControls : e,
		attachMedia : e,
		detachMedia : e,
		setState : function(h) {
			if (h === this.state) {
				return
			}
			var g = this.state || d.state.IDLE;
			this.state = h;
			this.sendEvent(d.JWPLAYER_PLAYER_STATE, {
				oldstate : g,
				newstate : h
			})
		}
	};
	b.html5.DefaultProvider = f
})(jwplayer);
(function(a) {
	function b(c) {
		if (a._.isObject(c) && a.html5.YoutubeProvider.supports(c)) {
			return a.html5.YoutubeProvider
		}
		return a.html5.VideoProvider
	}
	a.html5.chooseProvider = b
})(jwplayer);
(function(b) {
	var j = b.utils, k = b._, p = b.events, r = p.state, n = window.clearInterval, f = b.html5.DefaultProvider, q = j
			.isMSIE(), h = j.isMobile(), g = j.isSafari(), e = j
			.isAndroidNative(), c = j.isIOS(7);
	function d(t, s) {
		j.foreach(t, function(u, v) {
			s.addEventListener(u, v, false)
		})
	}
	function l(t, s) {
		j.foreach(t, function(u, v) {
			s.removeEventListener(u, v, false)
		})
	}
	function a(s) {
		return Math.floor(s * 10) / 10
	}
	function i(ah) {
		this.state = r.IDLE;
		var s = new b.events.eventdispatcher("provider." + this.name);
		j.extend(this, s);
		var u = this, ae = {
			abort : M,
			canplay : E,
			canplaythrough : M,
			click : J,
			durationchange : R,
			emptied : M,
			ended : z,
			error : A,
			loadeddata : M,
			loadedmetadata : E,
			loadstart : M,
			pause : ai,
			play : ai,
			playing : ai,
			progress : S,
			ratechange : M,
			readystatechange : M,
			seeked : V,
			seeking : q ? I : M,
			stalled : M,
			suspend : M,
			timeupdate : aj,
			volumechange : x,
			waiting : I,
			webkitbeginfullscreen : Q,
			webkitendfullscreen : ak
		}, N, aa, an, C, al = false, Z, af = 0, U = false, ab, B = -1, Y = -1, F = false, W, T = -1, L = false, am = false;
		this.sendEvent = function() {
			if (!F) {
				return
			}
			s.sendEvent.apply(this, arguments)
		};
		var G = document.getElementById(ah);
		var K = G.querySelector("video");
		K = K || document.createElement("video");
		d(ae, K);
		if (!c) {
			K.controls = true;
			K.controls = false
		}
		K.setAttribute("x-webkit-airplay", "allow");
		K.setAttribute("webkit-playsinline", "");
		F = true;
		function M() {
		}
		function J() {
			if (j.isMSIE(10)) {
			} else {
				u.sendEvent(p.JWPLAYER_PROVIDER_CLICK)
			}
		}
		function R(ap) {
			M(ap);
			if (!F) {
				return
			}
			var ao = a(K.duration);
			if (an !== ao) {
				an = ao
			}
			if (e && af > 0 && ao > af) {
				u.seek(af)
			}
			aj()
		}
		function aj(ao) {
			M(ao);
			S(ao);
			if (!F) {
				return
			}
			if (u.state === r.PLAYING && !U) {
				C = a(K.currentTime);
				if (ao) {
					al = true
				}
				u.sendEvent(p.JWPLAYER_MEDIA_TIME, {
					position : C,
					duration : an
				})
			}
		}
		function X() {
			u.sendEvent(p.JWPLAYER_MEDIA_META, {
				duration : K.duration,
				height : K.videoHeight,
				width : K.videoWidth
			})
		}
		function E(ao) {
			M(ao);
			if (!F) {
				return
			}
			if (!al) {
				al = true;
				D()
			}
			if (ao.type === "loadedmetadata") {
				if (K.muted) {
					K.muted = false;
					K.muted = true
				}
				X()
			}
		}
		function S(ao) {
			M(ao);
			if (al && af > 0 && !e) {
				if (q) {
					setTimeout(function() {
						if (af > 0) {
							u.seek(af)
						}
					}, 200)
				} else {
					u.seek(af)
				}
			}
		}
		function D() {
			if (!Z) {
				Z = true;
				u.sendEvent(p.JWPLAYER_MEDIA_BUFFER_FULL)
			}
		}
		function ai(ao) {
			M(ao);
			if (!F || U) {
				return
			}
			if (K.paused) {
				if (K.currentTime === K.duration && K.duration > 3) {
				} else {
					u.pause()
				}
			} else {
				if (j.isFF() && ao.type === "play" && u.state === r.BUFFERING) {
					return
				} else {
					u.setState(r.PLAYING)
				}
			}
		}
		function I(ao) {
			M(ao);
			if (!F) {
				return
			}
			if (!U) {
				u.setState(r.BUFFERING)
			}
		}
		function A() {
			if (!F) {
				return
			}
			j.log("Error playing media: %o %s", K.error, K.src || aa.file);
			u.sendEvent(p.JWPLAYER_MEDIA_ERROR, {
				message : "Error loading media: File could not be played"
			});
			u.setState(r.IDLE)
		}
		function y(ar) {
			var ao;
			if (j.typeOf(ar) === "array" && ar.length > 0) {
				ao = [];
				for (var aq = 0; aq < ar.length; aq++) {
					var at = ar[aq], ap = {};
					ap.label = ad(at) ? ad(at) : aq;
					ao[aq] = ap
				}
			}
			return ao
		}
		function w(ap) {
			var ao = y(ap);
			if (ao) {
				u.sendEvent(p.JWPLAYER_MEDIA_LEVELS, {
					levels : ao,
					currentQuality : T
				})
			}
		}
		function ad(ao) {
			if (ao.label) {
				return ao.label
			}
			return 0
		}
		function P() {
			if (T < 0) {
				T = 0
			}
			if (W) {
				var aq = j.getCookies(), ao = aq.qualityLabel;
				for (var ap = 0; ap < W.length; ap++) {
					if (W[ap]["default"]) {
						T = ap
					}
					if (ao && W[ap].label === ao) {
						T = ap;
						break
					}
				}
			}
		}
		function O() {
			return (h || g)
		}
		function H(ao, aq) {
			aa = W[T];
			n(B);
			B = setInterval(v, 100);
			af = 0;
			var ap = (K.src !== aa.file);
			if (ap || O()) {
				if (!h) {
					u.setState(r.BUFFERING)
				}
				al = false;
				Z = false;
				an = aq ? aq : -1;
				K.src = aa.file;
				K.load()
			} else {
				if (ao === 0) {
					af = -1;
					u.seek(ao)
				}
				X();
				K.play()
			}
			C = K.currentTime;
			if (h) {
				D()
			}
			if (j.isIOS() && u.getFullScreen()) {
				K.controls = true
			}
			if (ao > 0) {
				u.seek(ao)
			}
		}
		this.stop = function() {
			if (!F) {
				return
			}
			n(B);
			K.removeAttribute("src");
			if (!q) {
				K.load()
			}
			T = -1;
			this.setState(r.IDLE)
		};
		this.destroy = function() {
			l(ae, K);
			this.remove()
		};
		this.load = function(ao) {
			if (!F) {
				return
			}
			W = ao.sources;
			P();
			w(W);
			H(ao.starttime || 0, ao.duration)
		};
		this.play = function() {
			var ao = window.thisCurrentRate();
			if (j.isIE()) {
				b().onPause(function() {
					K.playbackRate = ao
				});
				b().onPlay(function() {
					K.playbackRate = ao
				})
			}
			K.playbackRate = Number(ao);
			if (F && !U) {
				K.play()
			}
		};
		this.pause = function() {
			if (F) {
				K.pause();
				this.setState(r.PAUSED)
			}
		};
		this.seekDrag = function(ao) {
			if (!F) {
				return
			}
			U = ao;
			if (ao) {
				K.pause()
			} else {
				K.play()
			}
		};
		this.seek = function(ap) {
			if (!F) {
				return
			}
			if (!U && af === 0) {
				this.sendEvent(p.JWPLAYER_MEDIA_SEEK, {
					position : C,
					offset : ap
				})
			}
			if (al) {
				af = 0;
				try {
					K.currentTime = ap
				} catch (ao) {
					af = ap
				}
			} else {
				af = ap
			}
		};
		function V(ao) {
			M(ao);
			if (!U && u.state !== r.PAUSED) {
				u.setState(r.PLAYING)
			}
		}
		this.volume = function(ao) {
			if (j.exists(ao)) {
				K.volume = Math.min(Math.max(0, ao / 100), 1);
				ab = K.volume * 100
			}
		};
		function x() {
			u.sendEvent(p.JWPLAYER_MEDIA_VOLUME, {
				volume : Math.round(K.volume * 100)
			});
			u.sendEvent(p.JWPLAYER_MEDIA_MUTE, {
				mute : K.muted
			})
		}
		this.mute = function(ao) {
			if (!j.exists(ao)) {
				ao = !K.muted
			}
			if (ao) {
				ab = K.volume * 100;
				K.muted = true
			} else {
				this.volume(ab);
				K.muted = false
			}
		};
		this.setState = function(ao) {
			if (ao === r.PAUSED && this.state === r.IDLE) {
				return
			}
			if (U) {
				return
			}
			f.setState.apply(this, arguments)
		};
		function v() {
			if (!F) {
				return
			}
			var ao = ac();
			if (ao >= 1) {
				n(B)
			}
			if (ao !== Y) {
				Y = ao;
				u.sendEvent(p.JWPLAYER_MEDIA_BUFFER, {
					bufferPercent : Math.round(Y * 100)
				})
			}
		}
		function ac() {
			var ao = K.buffered;
			if (!ao || !K.duration || ao.length === 0) {
				return 0
			}
			return ao.end(ao.length - 1) / K.duration
		}
		function z(ao) {
			M(ao);
			if (F) {
				ag()
			}
		}
		function ag() {
			if (u.state !== r.IDLE) {
				n(B);
				T = -1;
				L = true;
				u.sendEvent(p.JWPLAYER_MEDIA_BEFORECOMPLETE);
				if (F) {
					u.setState(r.IDLE);
					L = false;
					u.sendEvent(p.JWPLAYER_MEDIA_COMPLETE)
				}
			}
		}
		function Q(ao) {
			am = true;
			t(ao);
			if (j.isIOS()) {
				K.controls = false
			}
		}
		function ak(ao) {
			am = false;
			t(ao);
			if (j.isIOS()) {
				K.controls = false
			}
		}
		function t(ao) {
			u.sendEvent("fullscreenchange", {
				target : ao.target,
				jwstate : am
			})
		}
		this.checkComplete = function() {
			return L
		};
		this.detachMedia = function() {
			n(B);
			F = false;
			return K
		};
		this.attachMedia = function(ao) {
			F = true;
			if (!ao) {
				al = false
			}
			if (L) {
				this.setState(r.IDLE);
				this.sendEvent(p.JWPLAYER_MEDIA_COMPLETE);
				L = false
			}
		};
		this.setContainer = function(ao) {
			N = ao;
			ao.appendChild(K)
		};
		this.getContainer = function() {
			return N
		};
		this.remove = function() {
			if (K) {
				K.removeAttribute("src");
				if (!q) {
					K.load()
				}
			}
			n(B);
			T = -1;
			if (N === K.parentNode) {
				N.removeChild(K)
			}
		};
		this.setVisibility = function(ao) {
			ao = !!ao;
			if (ao || e) {
				j.css.style(N, {
					visibility : "visible",
					opacity : 1
				})
			} else {
				j.css.style(N, {
					visibility : "",
					opacity : 0
				})
			}
		};
		this.resize = function(aq, ap, ao) {
			return j.stretch(ao, K, aq, ap, K.videoWidth, K.videoHeight)
		};
		this.setControls = function(ao) {
			K.controls = !!ao
		};
		this.supportsFullscreen = k.constant(true);
		this.setFullScreen = function(ap) {
			ap = !!ap;
			if (ap) {
				try {
					var ao = K.webkitEnterFullscreen || K.webkitEnterFullScreen;
					if (ao) {
						ao.apply(K)
					}
				} catch (ar) {
					return false
				}
				return u.getFullScreen()
			} else {
				var aq = K.webkitExitFullscreen || K.webkitExitFullScreen;
				if (aq) {
					aq.apply(K)
				}
			}
			return ap
		};
		u.getFullScreen = function() {
			return am || !!K.webkitDisplayingFullscreen
		};
		this.isAudioFile = function() {
			if (!W) {
				return false
			}
			var ao = W[0].type;
			return (ao === "oga" || ao === "aac" || ao === "mp3" || ao === "vorbis")
		};
		this.setCurrentQuality = function(aq) {
			if (T === aq) {
				return
			}
			aq = parseInt(aq, 10);
			if (aq >= 0) {
				if (W && W.length > aq) {
					T = aq;
					j.saveCookie("qualityLabel", W[aq].label);
					this.sendEvent(p.JWPLAYER_MEDIA_LEVEL_CHANGED, {
						currentQuality : aq,
						levels : y(W)
					});
					var ap = a(K.currentTime);
					var ao = a(K.duration);
					if (ao <= 0) {
						ao = an
					}
					H(ap, ao)
				}
			}
		};
		this.getCurrentQuality = function() {
			return T
		};
		this.getQualityLevels = function() {
			return y(W)
		}
	}
	var o = function() {
	};
	o.prototype = f;
	i.prototype = new o();
	i.supports = k.constant(true);
	b.html5.VideoProvider = i
})(jwplayer);
(function(c) {
	var f = c.utils, g = c._, i = c.events, j = i.state, d = c.html5.DefaultProvider, a = new f.scriptloader(
			window.location.protocol + "//www.youtube.com/iframe_api"), e = f
			.isMobile();
	function b(N) {
		this.state = j.IDLE;
		var n = f.extend(this, new c.events.eventdispatcher("provider."
				+ this.name)), V = window.YT, r = null, L = document
				.createElement("div"), y, G = -1, H = false, t = null, l = null, R = -1, p = -1, I, v = false, F = e;
		this.setState = function(W) {
			clearInterval(R);
			if (W !== j.IDLE) {
				R = setInterval(u, 250);
				if (W === j.PLAYING) {
					O()
				} else {
					if (W === j.BUFFERING) {
						z()
					}
				}
			}
			d.setState.apply(this, arguments)
		};
		if (!V && a) {
			a.addEventListener(i.COMPLETE, A);
			a.addEventListener(i.ERROR, K);
			a.load()
		}
		L.id = N + "_youtube";
		function A() {
			if (window.YT && window.YT.loaded) {
				V = window.YT;
				J()
			} else {
				setTimeout(A, 100)
			}
		}
		function K() {
			a = null
		}
		function w() {
			var W = L && L.parentNode;
			if (!W) {
				if (!H) {
					c(N).onReady(J);
					H = true
				}
				return false
			}
			return W
		}
		function J() {
			if (V && w()) {
				if (t) {
					t.apply(n)
				}
			}
		}
		function u() {
			if (!r || !r.getPlayerState) {
				return
			}
			var W = r.getPlayerState();
			if (W !== null && W !== undefined && W !== p) {
				q({
					data : W
				})
			}
			var X = V.PlayerState;
			if (W === X.PLAYING) {
				Q()
			} else {
				if (W === X.BUFFERING) {
					z()
				}
			}
		}
		function S(W) {
			return Math.round(W * 10) / 10
		}
		function Q() {
			z();
			n.sendEvent(i.JWPLAYER_MEDIA_TIME, {
				position : S(r.getCurrentTime()),
				duration : r.getDuration()
			})
		}
		function z() {
			var W = 0;
			if (r && r.getVideoLoadedFraction) {
				W = Math.round(r.getVideoLoadedFraction() * 100)
			}
			if (G !== W) {
				G = W;
				n.sendEvent(i.JWPLAYER_MEDIA_BUFFER, {
					bufferPercent : W
				})
			}
		}
		function U() {
			if (n.state !== j.IDLE) {
				v = true;
				n.sendEvent(i.JWPLAYER_MEDIA_BEFORECOMPLETE);
				n.setState(j.IDLE);
				v = false;
				n.sendEvent(i.JWPLAYER_MEDIA_COMPLETE)
			}
		}
		function T() {
			n.sendEvent(i.JWPLAYER_MEDIA_META, {
				duration : r.getDuration(),
				width : L.clientWidth,
				height : L.clientHeight
			})
		}
		function B() {
			var W = arguments;
			var X = W.length - 1;
			return function() {
				var Z = X;
				var Y = W[X].apply(this, arguments);
				while (Z--) {
					Y = W[Z].call(this, Y)
				}
				return Y
			}
		}
		function x(W, Z) {
			if (!W) {
				throw "invalid Youtube ID"
			}
			var Y = L.parentNode;
			if (!Y) {
				return
			}
			var X = {
				height : "100%",
				width : "100%",
				videoId : W,
				playerVars : f.extend({
					html5 : 1,
					autoplay : 0,
					controls : 0,
					showinfo : 0,
					rel : 0,
					modestbranding : 0,
					playsinline : 1,
					origin : location.protocol + "//" + location.hostname
				}, Z),
				events : {
					onReady : P,
					onStateChange : q,
					onPlaybackQualityChange : E,
					onError : s
				}
			};
			n.setVisibility(true);
			r = new V.Player(L, X);
			L = r.getIframe();
			t = null;
			D();
			o()
		}
		function P() {
			if (l) {
				l.apply(n);
				l = null
			}
		}
		function q(W) {
			var X = V.PlayerState;
			p = W.data;
			switch (p) {
			case X.UNSTARTED:
				return;
			case X.ENDED:
				U();
				return;
			case X.PLAYING:
				if (g.isFunction(r.unloadModule)) {
					r.unloadModule("captions")
				}
				F = false;
				T();
				n.sendEvent(i.JWPLAYER_MEDIA_LEVELS, {
					levels : n.getQualityLevels(),
					currentQuality : n.getCurrentQuality()
				});
				n.setState(j.PLAYING);
				return;
			case X.PAUSED:
				n.setState(j.PAUSED);
				return;
			case X.BUFFERING:
				n.setState(j.BUFFERING);
				return;
			case X.CUED:
				n.setState(j.IDLE);
				return
			}
		}
		function E() {
			if (p !== V.PlayerState.ENDED) {
				n.play()
			}
			n.sendEvent(i.JWPLAYER_MEDIA_LEVEL_CHANGED, {
				currentQuality : n.getCurrentQuality(),
				levels : n.getQualityLevels()
			})
		}
		function s() {
			n.sendEvent(i.JWPLAYER_MEDIA_ERROR, {
				message : "Error loading YouTube: Video could not be played"
			})
		}
		function D() {
			if (e) {
				n.setVisibility(true);
				f.css("#" + N + " .jwcontrols", {
					display : "none"
				})
			}
		}
		function O() {
			f.css("#" + N + " .jwcontrols", {
				display : ""
			})
		}
		function M() {
			clearInterval(R);
			if (r && r.stopVideo) {
				try {
					r.stopVideo();
					r.clearVideo()
				} catch (W) {
				}
			}
		}
		this.init = function(W) {
			C(W)
		};
		this.destroy = function() {
			this.remove();
			y = L = V = n = null
		};
		this.load = function(W) {
			this.setState(j.BUFFERING);
			C(W);
			n.play()
		};
		function C(Y) {
			l = null;
			var X = Y.sources[0].file;
			var ab = f.youTubeID(X);
			if (!Y.image) {
				Y.image = "//i.ytimg.com/vi/" + ab + "/0.jpg"
			}
			n.setVisibility(true);
			if (!V || !r) {
				t = function() {
					x(ab)
				};
				J();
				return
			}
			if (!r.getPlayerState) {
				var W = function() {
					o();
					n.load(Y)
				};
				if (l) {
					l = B(W, l)
				} else {
					l = W
				}
				return
			}
			var aa = r.getVideoData().video_id;
			if (aa !== ab) {
				if (F) {
					M();
					r.cueVideoById(ab)
				} else {
					r.loadVideoById(ab)
				}
				var Z = r.getPlayerState();
				var ac = V.PlayerState;
				if (Z === ac.UNSTARTED || Z === ac.CUED) {
					D()
				}
			} else {
				if (r.getCurrentTime() > 0) {
					r.seekTo(0)
				}
				T()
			}
		}
		this.stop = function() {
			M();
			this.setState(j.IDLE)
		};
		this.play = function() {
			if (F) {
				return
			}
			if (r && r.playVideo) {
				r.playVideo()
			} else {
				if (l) {
					l = B(this.play, l)
				} else {
					l = this.play
				}
			}
		};
		this.pause = function() {
			if (F) {
				return
			}
			if (r.pauseVideo) {
				r.pauseVideo()
			}
		};
		this.seek = function(W) {
			if (F) {
				return
			}
			if (r.seekTo) {
				r.seekTo(W)
			}
		};
		this.volume = function(W) {
			if (!r || !r.getVolume) {
				return
			}
			if (f.exists(W)) {
				I = Math.min(Math.max(0, W), 100);
				r.setVolume(I)
			}
		};
		function o() {
			if (!r || !r.getVolume) {
				return
			}
			n.sendEvent(i.JWPLAYER_MEDIA_VOLUME, {
				volume : Math.round(r.getVolume())
			});
			n.sendEvent(i.JWPLAYER_MEDIA_MUTE, {
				mute : r.isMuted()
			})
		}
		this.mute = function(W) {
			if (!r || !r.getVolume) {
				return
			}
			if (!f.exists(W)) {
				W = !r.isMuted()
			}
			if (W) {
				I = r.getVolume();
				r.mute()
			} else {
				this.volume(I);
				r.unMute()
			}
		};
		this.detachMedia = function() {
			return document.createElement("video")
		};
		this.attachMedia = function() {
			if (v) {
				this.setState(j.IDLE);
				this.sendEvent(i.JWPLAYER_MEDIA_COMPLETE);
				v = false
			}
		};
		this.setContainer = function(W) {
			y = W;
			W.appendChild(L);
			this.setVisibility(true)
		};
		this.getContainer = function() {
			return y
		};
		this.supportsFullscreen = function() {
			return !!(y && (y.requestFullscreen || y.requestFullScreen
					|| y.webkitRequestFullscreen || y.webkitRequestFullScreen
					|| y.webkitEnterFullscreen || y.webkitEnterFullScreen
					|| y.mozRequestFullScreen || y.msRequestFullscreen))
		};
		this.remove = function() {
			M();
			if (L && y && y === L.parentNode) {
				y.removeChild(L)
			}
			t = l = r = null
		};
		this.setVisibility = function(W) {
			W = !!W;
			if (W) {
				f.css.style(L, {
					display : "block"
				});
				f.css.style(y, {
					visibility : "visible",
					opacity : 1
				})
			} else {
				if (!e) {
					f.css.style(y, {
						opacity : 0
					})
				}
			}
		};
		this.resize = function(Y, X, W) {
			return f.stretch(W, L, Y, X, L.clientWidth, L.clientHeight - 40)
		};
		this.checkComplete = function() {
			return v
		};
		this.getCurrentQuality = function() {
			if (!r) {
				return
			}
			if (r.getAvailableQualityLevels) {
				var X = r.getPlaybackQuality();
				var W = r.getAvailableQualityLevels();
				return W.indexOf(X)
			}
			return -1
		};
		this.getQualityLevels = function() {
			if (!r) {
				return
			}
			if (!g.isFunction(r.getAvailableQualityLevels)) {
				return []
			}
			var X = r.getAvailableQualityLevels();
			if (X.length === 2 && g.contains(X, "auto")) {
				return {
					label : g.without(X, "auto")
				}
			}
			var W = g.map(X, function(Y) {
				return {
					label : Y
				}
			});
			return W.reverse()
		};
		this.setCurrentQuality = function(Y) {
			if (!r) {
				return
			}
			if (r.getAvailableQualityLevels) {
				var X = r.getAvailableQualityLevels();
				if (X.length) {
					var W = X[X.length - Y - 1];
					r.setPlaybackQuality(W)
				}
			}
		}
	}
	window.onYouTubeIframeAPIReady = function() {
		a = null
	};
	function k(l) {
		return (f.isYouTube(l.file, l.type))
	}
	var h = function() {
	};
	h.prototype = d;
	b.prototype = new h();
	b.supports = k;
	c.html5.YoutubeProvider = b
})(jwplayer);
(function(b) {
	var l = b.utils, h = l.css, c = b.events, d = "jwskip", e = "jwskipimage", g = "jwskipover", f = "jwskipout", k = 80, i = 30, j = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAkAAAAICAYAAAArzdW1AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAA3NpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDE0IDc5LjE1MTQ4MSwgMjAxMy8wMy8xMy0xMjowOToxNSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDo0ODkzMWI3Ny04YjE5LTQzYzMtOGM2Ni0wYzdkODNmZTllNDYiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6RDI0OTcxRkE0OEM2MTFFM0I4MTREM0ZBQTFCNDE3NTgiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6RDI0OTcxRjk0OEM2MTFFM0I4MTREM0ZBQTFCNDE3NTgiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIChNYWNpbnRvc2gpIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6NDA5ZGQxNDktNzdkMi00M2E3LWJjYWYtOTRjZmM2MWNkZDI0IiBzdFJlZjpkb2N1bWVudElEPSJ4bXAuZGlkOjQ4OTMxYjc3LThiMTktNDNjMy04YzY2LTBjN2Q4M2ZlOWU0NiIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/PqAZXX0AAABYSURBVHjafI2BCcAwCAQ/kr3ScRwjW+g2SSezCi0kYHpwKLy8JCLDbWaGTM+MAFzuVNXhNiTQsh+PS9QhZ7o9JuFMeUVNwjsamDma4K+3oy1cqX/hxyPAAAQwNKV27g9PAAAAAElFTkSuQmCC", a = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAkAAAAICAYAAAArzdW1AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAA3NpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDE0IDc5LjE1MTQ4MSwgMjAxMy8wMy8xMy0xMjowOToxNSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDo0ODkzMWI3Ny04YjE5LTQzYzMtOGM2Ni0wYzdkODNmZTllNDYiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6RDI0OTcxRkU0OEM2MTFFM0I4MTREM0ZBQTFCNDE3NTgiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6RDI0OTcxRkQ0OEM2MTFFM0I4MTREM0ZBQTFCNDE3NTgiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIChNYWNpbnRvc2gpIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6NDA5ZGQxNDktNzdkMi00M2E3LWJjYWYtOTRjZmM2MWNkZDI0IiBzdFJlZjpkb2N1bWVudElEPSJ4bXAuZGlkOjQ4OTMxYjc3LThiMTktNDNjMy04YzY2LTBjN2Q4M2ZlOWU0NiIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/PvgIj/QAAABYSURBVHjadI6BCcAgDAS/0jmyih2tm2lHSRZJX6hQQ3w4FP49LKraSHV3ZLDzAuAi3cwaqUhSfvft+EweznHneUdTzPGRmp5hEJFhAo3LaCnjn7blzCvAAH9YOSCL5RZKAAAAAElFTkSuQmCC";
	b.html5.adskipbutton = function(K, D, p, I) {
		var n, r, E = -1, J = false, C, s = 0, q, v, x = false, A = l.extend(
				this, new c.eventdispatcher());
		function t() {
			q = new Image();
			q.src = j;
			q.className = e + " " + f;
			v = new Image();
			v.src = a;
			v.className = e + " " + g;
			n = G("div", d);
			n.id = K + "_skipcontainer";
			r = G("canvas");
			n.appendChild(r);
			A.width = r.width = k;
			A.height = r.height = i;
			n.appendChild(v);
			n.appendChild(q);
			h.style(n, {
				visibility : "hidden",
				bottom : D
			});
			n.addEventListener("mouseover", y);
			n.addEventListener("mouseout", w);
			if (l.isMobile()) {
				var L = new l.touch(n);
				L.addEventListener(l.touchEvents.TAP, B)
			} else {
				n.addEventListener("click", B)
			}
		}
		function H(L) {
			if (E < 0) {
				return
			}
			var M = p.replace(/xx/gi, Math.ceil(E - L));
			z(M)
		}
		function o(N, M) {
			if (l.typeOf(s) === "number") {
				E = s
			} else {
				if (s.slice(-1) === "%") {
					var L = parseFloat(s.slice(0, -1));
					if (M && !isNaN(L)) {
						E = M * L / 100
					}
				} else {
					if (l.typeOf(s) === "string") {
						E = l.seconds(s)
					} else {
						if (!isNaN(s)) {
							E = s
						}
					}
				}
			}
		}
		A.updateSkipTime = function(M, L) {
			o(M, L);
			if (E >= 0) {
				h.style(n, {
					visibility : C ? "visible" : "hidden"
				});
				if (E - M > 0) {
					H(M);
					if (J) {
						J = false;
						n.style.cursor = "default"
					}
				} else {
					if (!J) {
						if (!J) {
							J = true;
							n.style.cursor = "pointer"
						}
						if (x) {
							F()
						} else {
							z()
						}
					}
				}
			}
		};
		function B() {
			if (J) {
				A.sendEvent(c.JWPLAYER_AD_SKIPPED)
			}
		}
		this.reset = function(L) {
			J = false;
			s = L;
			o(0, 0);
			H(0)
		};
		function y() {
			x = true;
			if (J) {
				F()
			}
		}
		function w() {
			x = false;
			if (J) {
				z()
			}
		}
		function z(N) {
			N = N || I;
			var M = r.getContext("2d");
			M.clearRect(0, 0, k, i);
			u(M, 0, 0, k, i, 5, true, false, false);
			u(M, 0, 0, k, i, 5, false, true, false);
			M.fillStyle = "#979797";
			M.globalAlpha = 1;
			var O = r.height / 2;
			var L = r.width / 2;
			M.textAlign = "center";
			M.font = "Bold 12px Sans-Serif";
			if (N === I) {
				L -= q.width;
				M.drawImage(q, r.width
						- ((r.width - M.measureText(I).width) / 2) - 4,
						(i - q.height) / 2)
			}
			M.fillText(N, L, O + 4)
		}
		function F(N) {
			N = N || I;
			var M = r.getContext("2d");
			M.clearRect(0, 0, k, i);
			u(M, 0, 0, k, i, 5, true, false, true);
			u(M, 0, 0, k, i, 5, false, true, true);
			M.fillStyle = "#FFFFFF";
			M.globalAlpha = 1;
			var O = r.height / 2;
			var L = r.width / 2;
			M.textAlign = "center";
			M.font = "Bold 12px Sans-Serif";
			if (N === I) {
				L -= q.width;
				M.drawImage(v, r.width
						- ((r.width - M.measureText(I).width) / 2) - 4,
						(i - q.height) / 2)
			}
			M.fillText(N, L, O + 4)
		}
		function u(T, P, O, L, Q, M, R, S, N) {
			if (typeof S === "undefined") {
				S = true
			}
			if (typeof M === "undefined") {
				M = 5
			}
			T.beginPath();
			T.moveTo(P + M, O);
			T.lineTo(P + L - M, O);
			T.quadraticCurveTo(P + L, O, P + L, O + M);
			T.lineTo(P + L, O + Q - M);
			T.quadraticCurveTo(P + L, O + Q, P + L - M, O + Q);
			T.lineTo(P + M, O + Q);
			T.quadraticCurveTo(P, O + Q, P, O + Q - M);
			T.lineTo(P, O + M);
			T.quadraticCurveTo(P, O, P + M, O);
			T.closePath();
			if (S) {
				T.strokeStyle = "white";
				T.globalAlpha = N ? 1 : 0.25;
				T.stroke()
			}
			if (R) {
				T.fillStyle = "#000000";
				T.globalAlpha = 0.5;
				T.fill()
			}
		}
		A.show = function() {
			C = true;
			if (E > 0) {
				h.style(n, {
					visibility : "visible"
				})
			}
		};
		A.hide = function() {
			C = false;
			h.style(n, {
				visibility : "hidden"
			})
		};
		function G(M, L) {
			var N = document.createElement(M);
			if (L) {
				N.className = L
			}
			return N
		}
		this.element = function() {
			return n
		};
		t()
	};
	h("." + d, {
		position : "absolute",
		"float" : "right",
		display : "inline-block",
		width : k,
		height : i,
		right : 10
	});
	h("." + e, {
		position : "relative",
		display : "none"
	})
})(window.jwplayer);
(function(f) {
	var i = f.html5, n = f.utils, p = f.events, q = p.state, o = f.parsers, l = n.css, d = n
			.isAndroid(4, true), k = "playing", a = ".jwcaptions", b = "absolute", c = "none", j = "100%", g = "hidden", h = "normal", e = "#FFFFFF";
	i.captions = function(N, z) {
		var U = N, A, R = {
			back : true,
			color : e,
			fontSize : 15,
			fontFamily : "Arial,sans-serif",
			fontOpacity : 100,
			backgroundColor : "#000",
			backgroundOpacity : 100,
			edgeStyle : null,
			windowColor : e,
			windowOpacity : 0
		}, Q = {
			fontStyle : h,
			fontWeight : h,
			textDecoration : c
		}, X, W, C, x = [], G = 0, L = -1, w = 0, Y = false, F = new p.eventdispatcher();
		n.extend(this, F);
		function J() {
			A = document.createElement("div");
			A.id = U.id + "_caption";
			A.className = "jwcaptions";
			U.jwAddEventListener(p.JWPLAYER_PLAYER_STATE, H);
			U.jwAddEventListener(p.JWPLAYER_PLAYLIST_ITEM, T);
			U.jwAddEventListener(p.JWPLAYER_MEDIA_ERROR, S);
			U.jwAddEventListener(p.JWPLAYER_ERROR, S);
			U.jwAddEventListener(p.JWPLAYER_READY, y);
			U.jwAddEventListener(p.JWPLAYER_MEDIA_TIME, r);
			U.jwAddEventListener(p.JWPLAYER_FULLSCREEN, B);
			U.jwAddEventListener(p.JWPLAYER_RESIZE, s)
		}
		function s() {
			v(false)
		}
		function S(aa) {
			n.log("CAPTIONS(" + aa + ")")
		}
		function M() {
			W = "idle";
			v(false)
		}
		function H(aa) {
			switch (aa.newstate) {
			case q.IDLE:
				M();
				break;
			case q.PLAYING:
				E();
				break
			}
		}
		function B(aa) {
			Y = aa.fullscreen;
			if (aa.fullscreen) {
				O();
				setTimeout(O, 500)
			} else {
				v(true)
			}
		}
		function O() {
			var aa = A.offsetHeight, ab = A.offsetWidth;
			if (aa !== 0 && ab !== 0) {
				X.resize(ab, Math.round(aa * 0.94))
			}
		}
		function T() {
			C = 0;
			x = [];
			X.update(0);
			G = 0;
			var ai = U.jwGetPlaylist()[U.jwGetPlaylistIndex()], af = ai.tracks, ae = [], ad = 0, ag = "", aa = 0, ab = "", ah;
			for (ad = 0; ad < af.length; ad++) {
				var ac = af[ad].kind.toLowerCase();
				if (ac === "captions" || ac === "subtitles") {
					ae.push(af[ad])
				}
			}
			w = 0;
			if (d) {
				return
			}
			for (ad = 0; ad < ae.length; ad++) {
				ab = ae[ad].file;
				if (ab) {
					if (!ae[ad].label) {
						ae[ad].label = ad.toString()
					}
					x.push(ae[ad]);
					Z(x[ad].file, ad)
				}
			}
			for (ad = 0; ad < x.length; ad++) {
				if (x[ad]["default"]) {
					aa = ad + 1;
					break
				}
			}
			ah = n.getCookies();
			ag = ah.captionLabel;
			if (ag) {
				af = u();
				for (ad = 0; ad < af.length; ad++) {
					if (ag === af[ad].label) {
						aa = ad;
						break
					}
				}
			}
			if (aa > 0) {
				t(aa)
			}
			v(false);
			V(p.JWPLAYER_CAPTIONS_LIST, u(), w)
		}
		function Z(ab, aa) {
			n.ajax(ab, function(ac) {
				D(ac, aa)
			}, K, true)
		}
		function D(ab, aa) {
			var af = ab.responseXML ? ab.responseXML.firstChild : null, ae;
			G++;
			if (af) {
				if (o.localName(af) === "xml") {
					af = af.nextSibling
				}
				while (af.nodeType === af.COMMENT_NODE) {
					af = af.nextSibling
				}
			}
			if (af && o.localName(af) === "tt") {
				ae = new f.parsers.dfxp()
			} else {
				ae = new f.parsers.srt()
			}
			try {
				var ac = ae.parse(ab.responseText);
				if (C < x.length) {
					x[aa].data = ac
				}
				v(false)
			} catch (ad) {
				S(ad.message + ": " + x[aa].file)
			}
			if (G === x.length) {
				if (L > 0) {
					t(L);
					L = -1
				}
				P()
			}
		}
		function K(aa) {
			G++;
			S(aa);
			if (G === x.length) {
				if (L > 0) {
					t(L);
					L = -1
				}
				P()
			}
		}
		function P() {
			var ab = [];
			for (var aa = 0; aa < x.length; aa++) {
				ab.push(x[aa])
			}
			F.sendEvent(p.JWPLAYER_CAPTIONS_LOADED, {
				captionData : ab
			})
		}
		function E() {
			W = k;
			v(false)
		}
		function v(aa) {
			if (!x.length) {
				X.hide()
			} else {
				if (W === k && w > 0) {
					X.show();
					if (Y) {
						B({
							fullscreen : true
						});
						return
					}
					I();
					if (aa) {
						setTimeout(I, 500)
					}
				} else {
					X.hide()
				}
			}
		}
		function I() {
			X.resize()
		}
		function y() {
			n.foreach(R, function(aa, ab) {
				if (z) {
					if (z[aa] !== undefined) {
						ab = z[aa]
					} else {
						if (z[aa.toLowerCase()] !== undefined) {
							ab = z[aa.toLowerCase()]
						}
					}
				}
				Q[aa] = ab
			});
			X = new f.html5.captions.renderer(Q, A);
			v(false)
		}
		function t(aa) {
			if (aa > 0) {
				C = aa - 1;
				w = Math.floor(aa)
			} else {
				w = 0;
				v(false);
				return
			}
			if (C >= x.length) {
				return
			}
			if (x[C].data) {
				X.populate(x[C].data)
			} else {
				if (G === x.length) {
					S("file not loaded: " + x[C].file);
					if (w !== 0) {
						V(p.JWPLAYER_CAPTIONS_CHANGED, x, 0)
					}
					w = 0
				} else {
					L = aa
				}
			}
			v(false)
		}
		function r(aa) {
			X.update(aa.position)
		}
		function V(ad, ac, ab) {
			var aa = {
				type : ad,
				tracks : ac,
				track : ab
			};
			F.sendEvent(ad, aa)
		}
		function u() {
			var ab = [ {
				label : "Off"
			} ];
			for (var aa = 0; aa < x.length; aa++) {
				ab.push({
					label : x[aa].label
				})
			}
			return ab
		}
		this.element = function() {
			return A
		};
		this.getCaptionsList = function() {
			return u()
		};
		this.getCurrentCaptions = function() {
			return w
		};
		this.setCurrentCaptions = function(ab) {
			if (ab >= 0 && w !== ab && ab <= x.length) {
				t(ab);
				var aa = u();
				n.saveCookie("captionLabel", aa[w].label);
				V(p.JWPLAYER_CAPTIONS_CHANGED, aa, w)
			}
		};
		J()
	};
	l(a, {
		position : b,
		cursor : "pointer",
		width : j,
		height : j,
		overflow : g
	})
})(jwplayer);
(function(d) {
	var c = d.html5, b = d.utils, a = b.css.style;
	c.captions.renderer = function(t, i) {
		var s, h, n, r, l, o, e = "visible", g = -1;
		this.hide = function() {
			clearInterval(g);
			a(h, {
				display : "none"
			})
		};
		this.populate = function(u) {
			l = -1;
			s = u;
			f()
		};
		function p(u) {
			u = u || "";
			e = "hidden";
			a(h, {
				visibility : e
			});
			r.innerHTML = u;
			if (u.length) {
				e = "visible";
				setTimeout(q, 16)
			}
		}
		this.resize = function() {
			q()
		};
		function q() {
			if (e === "visible") {
				var v = h.clientWidth, w = Math.pow(v / 400, 0.6);
				var u = t.fontSize * w;
				a(r, {
					maxWidth : v + "px",
					fontSize : Math.round(u) + "px",
					lineHeight : Math.round(u * 1.4) + "px",
					padding : Math.round(1 * w) + "px " + Math.round(8 * w)
							+ "px"
				});
				if (t.windowOpacity) {
					a(n, {
						padding : Math.round(5 * w) + "px",
						borderRadius : Math.round(5 * w) + "px"
					})
				}
				a(h, {
					visibility : e
				})
			}
		}
		function f() {
			var v = -1;
			for (var u = 0; u < s.length; u++) {
				if (s[u].begin <= o
						&& (u === s.length - 1 || s[u + 1].begin >= o)) {
					v = u;
					break
				}
			}
			if (v === -1) {
				p("")
			} else {
				if (v !== l) {
					l = v;
					p(s[u].text)
				}
			}
		}
		function j() {
			var v = t.fontOpacity, z = t.windowOpacity, y = t.edgeStyle, w = t.backgroundColor, u = {
				display : "inline-block"
			}, x = {
				color : b.hexToRgba(b.rgbHex(t.color), v),
				display : "inline-block",
				fontFamily : t.fontFamily,
				fontStyle : t.fontStyle,
				fontWeight : t.fontWeight,
				textAlign : "center",
				textDecoration : t.textDecoration,
				wordWrap : "break-word"
			};
			if (z) {
				u.backgroundColor = b.hexToRgba(b.rgbHex(t.windowColor), z)
			}
			k(y, x, v);
			if (t.back) {
				x.backgroundColor = b.hexToRgba(b.rgbHex(w),
						t.backgroundOpacity)
			} else {
				if (y === null) {
					k("uniform", x)
				}
			}
			h = document.createElement("div");
			n = document.createElement("div");
			r = document.createElement("span");
			a(h, {
				display : "block",
				height : "auto",
				position : "absolute",
				bottom : "20px",
				textAlign : "center",
				width : "100%"
			});
			a(n, u);
			a(r, x);
			n.appendChild(r);
			h.appendChild(n);
			i.appendChild(h)
		}
		function k(x, w, v) {
			var u = b.hexToRgba("#000000", v);
			if (x === "dropshadow") {
				w.textShadow = "0 2px 1px " + u
			} else {
				if (x === "raised") {
					w.textShadow = "0 0 5px " + u + ", 0 1px 5px " + u
							+ ", 0 2px 5px " + u
				} else {
					if (x === "depressed") {
						w.textShadow = "0 -2px 1px " + u
					} else {
						if (x === "uniform") {
							w.textShadow = "-2px 0 1px " + u + ",2px 0 1px "
									+ u + ",0 -2px 1px " + u + ",0 2px 1px "
									+ u + ",-1px 1px 1px " + u
									+ ",1px 1px 1px " + u + ",1px -1px 1px "
									+ u + ",1px 1px 1px " + u
						}
					}
				}
			}
		}
		this.show = function() {
			a(h, {
				display : "block"
			});
			q();
			clearInterval(g);
			g = setInterval(q, 250)
		};
		this.update = function(u) {
			o = u;
			if (s) {
				f()
			}
		};
		j()
	}
})(jwplayer);
(function(i, t, g) {
	var p = i.jwplayer, j = p.html5, y = p.utils, C = p._, b = p.events, f = b.state, s = y.css, z = y.transitionStyle, w = y
			.isMobile(), e = y.isAndroid(4, true), A = (i.top !== i.self), c = "button", r = "text", h = "divider", u = "slider", B = 250, v = {
		display : "none"
	}, k = {
		display : "block"
	}, a = {
		display : ""
	};
	function q(F, E) {
		var D = C.indexOf(F, E);
		if (D > -1) {
			F.splice(D, 1)
		}
	}
	function o(F, E) {
		var D = C.indexOf(F, E);
		if (D === -1) {
			F.push(E)
		}
	}
	function d(E, D) {
		return E + "_" + D
	}
	function x(D) {
		return D ? parseInt(D.width, 10) + "px " + parseInt(D.height, 10)
				+ "px" : "0 0"
	}
	var n;
	var l;
	j.controlbar = function(cF, aA, cl, cO) {
		aA = aA || {};
		if (cO == g || cO == null) {
			cO = true
		}
		n = cl;
		l = cO;
		if (n == true) {
			var cH, cq = bh("divider", h), a5 = {
				margin : 8,
				maxwidth : 2800,
				font : "Microsoft YaHei,Arial,sans-serif",
				fontsize : 12,
				fontcolor : parseInt("eeeeee", 16),
				fontweight : "bold",
				layout : {
					left : {
						position : "left",
						elements : [ bh("play", c), bh("elapsed", r) ]
					},
					center : {
						position : "center",
						elements : [ bh("time", u), bh("alt", r) ]
					},
					right : {
						position : "right",
						elements : [ bh("duration", r), bh("cc", c),
								bh("mute", c), bh("volume", u),
								bh("volumeH", u), bh("speed", c), bh("hd", c),
								bh("cast", c), bh("set", c),
								bh("fullscreen", c) ]
					}
				}
			}
		} else {
			var cH, cq = bh("divider", h), a5 = {
				margin : 8,
				maxwidth : 2800,
				font : "Microsoft YaHei,Arial,sans-serif",
				fontsize : 12,
				fontcolor : parseInt("eeeeee", 16),
				fontweight : "bold",
				layout : {
					left : {
						position : "left",
						elements : [ bh("play", c), bh("elapsed", r) ]
					},
					center : {
						position : "center",
						elements : [ bh("time", u), bh("alt", r) ]
					},
					right : {
						position : "right",
						elements : [ bh("duration", r), bh("cc", c),
								bh("mute", c), bh("volume", u),
								bh("volumeH", u), bh("hd", c), bh("cast", c),
								bh("fullscreen", c) ]
					}
				}
			}
		}
		var bB, aN, a2, aT, G = "small", cm, aK, cI, cu, by, aZ = [], aj, b8, cJ, bz, cM = {}, ba, aB, bV, N, b9, be, co, a1, U, aS, M, cU, bq, an, Q, ad = -1, bw = false, bf = false, bl = false, cr = false, ac = null, cc = 0, bP = [], F, bb = {
			play : "pause",
			mute : "unmute",
			cast : "casting",
			fullscreen : "normalscreen"
		}, cb = {
			play : false,
			mute : false,
			cast : false,
			fullscreen : aA.fullscreen || false
		}, ar = {
			play : aW,
			mute : bN,
			fullscreen : ap,
			next : bO,
			prev : cY,
			hd : cW,
			cc : a6,
			cast : b0
		}, aG = {
			time : aJ,
			volume : ck
		}, bk = {}, bX = [], bK = y.extend(this, new b.eventdispatcher());
		function bh(c2, c4, c3) {
			return {
				name : c2,
				type : c4,
				className : c3
			}
		}
		function al() {
			a2 = {};
			cI = cF.id + "_controlbar";
			cu = by = 0;
			aK = ce();
			aK.id = cI;
			aK.className = "jwcontrolbar";
			cH = cF.skin;
			aN = cH.getComponentLayout("controlbar");
			if (!aN) {
				aN = a5.layout
			}
			y.clearCss(af());
			s.block(cI + "build");
			cf();
			at();
			s.unblock(cI + "build");
			L();
			setTimeout(aD, 0);
			c0();
			bK.visible = true;
			aa();
			ci();
			aI();
			bK.railSmall()
		}
		function L() {
			cF.jwAddEventListener(b.JWPLAYER_MEDIA_TIME, aR);
			cF.jwAddEventListener(b.JWPLAYER_PLAYER_STATE, cL);
			cF.jwAddEventListener(b.JWPLAYER_PLAYLIST_ITEM, bR);
			cF.jwAddEventListener(b.JWPLAYER_MEDIA_MUTE, aD);
			cF.jwAddEventListener(b.JWPLAYER_MEDIA_VOLUME, aD);
			cF.jwAddEventListener(b.JWPLAYER_MEDIA_BUFFER, cK);
			cF.jwAddEventListener(b.JWPLAYER_FULLSCREEN, ch);
			cF.jwAddEventListener(b.JWPLAYER_PLAYLIST_LOADED, c0);
			cF.jwAddEventListener(b.JWPLAYER_MEDIA_LEVELS, ax);
			cF.jwAddEventListener(b.JWPLAYER_MEDIA_LEVEL_CHANGED, cs);
			cF.jwAddEventListener(b.JWPLAYER_CAPTIONS_LIST, cR);
			cF.jwAddEventListener(b.JWPLAYER_CAPTIONS_CHANGED, I);
			cF.jwAddEventListener(b.JWPLAYER_RESIZE, bY);
			cF.jwAddEventListener(b.JWPLAYER_CAST_AVAILABLE, aa);
			cF.jwAddEventListener(b.JWPLAYER_CAST_SESSION, aC);
			if (!w) {
				aK.addEventListener("mouseover", function() {
					i.addEventListener("mousedown", cz, false)
				}, false);
				aK.addEventListener("mouseout", function() {
					i.removeEventListener("mousedown", cz);
					t.onselectstart = null
				}, false)
			}
			aK.onmouseover = function(c2) {
				if (cF.jwGetState() == f.BUFFERING) {
					return
				}
				if (bW(c2, this)) {
					bK.railBig()
				}
			};
			aK.onmouseout = function(c2) {
				if (bW(c2, this)) {
					if (cF.jwGetState() != f.PAUSED) {
						bK.railSmall()
					}
				}
			}
		}
		function aO(c2, c3) {
			if (c2.contains) {
				return c2 != c3 && c2.contains(c3)
			} else {
				return !!(c2.compareDocumentPosition(c3) & 16)
			}
		}
		function bW(c3, c2) {
			if (ab(c3).type == "mouseover") {
				return !aO(c2, ab(c3).relatedTarget || ab(c3).fromElement)
						&& !((ab(c3).relatedTarget || ab(c3).fromElement) === c2)
			} else {
				return !aO(c2, ab(c3).relatedTarget || ab(c3).toElement)
						&& !((ab(c3).relatedTarget || ab(c3).toElement) === c2)
			}
		}
		function ab(c2) {
			return c2 || i.event
		}
		function bY() {
			aB = y.bounds(aK);
			if (aB.width > 0) {
				bK.show(true)
			}
		}
		function a8(c2) {
			var c4 = (c2.duration === Number.POSITIVE_INFINITY);
			var c3 = (c2.duration === 0 && c2.position !== 0 && y.isSafari() && !w);
			return c4 || c3
		}
		function aR(c2) {
			s.block(cI);
			if (a8(c2)) {
				bK.setText(cF.jwGetPlaylist()[cF.jwGetPlaylistIndex()].title
						|| "Live broadcast");
				am(false)
			} else {
				var c3;
				if (a2.elapsed) {
					c3 = y.timeFormat(c2.position);
					a2.elapsed.innerHTML = c3 + " / "
							+ y.timeFormat(c2.duration)
				}
				if (a2.duration) {
					c3 = y.timeFormat(c2.duration);
					a2.duration.innerHTML = ""
				}
				if (c2.duration > 0) {
					Y(c2.position / c2.duration)
				} else {
					Y(0)
				}
				cu = c2.duration;
				by = c2.position;
				if (!bf) {
					bK.setText()
				}
			}
		}
		function cL(c2) {
			switch (c2.newstate) {
			case f.BUFFERING:
			case f.PLAYING:
				if (a2.timeSliderThumb) {
					s.style(a2.timeSliderThumb, {
						opacity : 1
					})
				}
				a0("play", true);
				break;
			case f.PAUSED:
				if (!ac) {
					a0("play", false)
				}
				bK.railBig();
				break;
			case f.IDLE:
				a0("play", false);
				if (a2.timeSliderThumb) {
					s.style(a2.timeSliderThumb, {
						opacity : 0
					})
				}
				if (a2.timeRail) {
					a2.timeRail.className = "jwrail"
				}
				a9(0);
				aR({
					position : 0,
					duration : 0
				});
				break
			}
		}
		function bR(c2) {
			if (!bf) {
				var c4 = cF.jwGetPlaylist()[c2.index].tracks, c3 = false, c6 = false;
				ay();
				if (C.isArray(c4) && !w) {
					for (var c5 = 0; c5 < c4.length; c5++) {
						if (!c3 && c4[c5].file && c4[c5].kind
								&& c4[c5].kind.toLowerCase() === "thumbnails") {
							co.load(c4[c5].file);
							c3 = true
						}
						if (c4[c5].file && c4[c5].kind
								&& c4[c5].kind.toLowerCase() === "chapters") {
							aE(c4[c5].file);
							c6 = true
						}
					}
				}
				if (!c3) {
					co.load()
				}
			}
		}
		function aD() {
			var c2 = cF.jwGetMute();
			bz = cF.jwGetVolume() / 100;
			a0("mute", c2 || bz === 0);
			aM(c2 ? 0 : bz)
		}
		function cK(c2) {
			a9(c2.bufferPercent / 100)
		}
		var ah = false;
		function ch(c2) {
			a0("fullscreen", c2.fullscreen);
			aw();
			if (cF.jwGetFullscreen()) {
				ah = true
			}
		}
		function c0() {
			s.style([ a2.hd, a2.cc ], v);
			aw();
			aF()
		}
		function bM() {
			return (!bf && aZ.length > 1 && M)
		}
		function ax(c2) {
			aZ = c2.levels || [];
			if (bM()) {
				s.style(a2.hd, a);
				M.clearOptions();
				for (var c3 = 0; c3 < aZ.length; c3++) {
					M.addOption(aZ[c3].label, c3)
				}
				cs(c2)
			} else {
				s.style(a2.hd, v)
			}
			aF()
		}
		function cs(c2) {
			aj = Math.floor(c2.currentQuality);
			if (a2.hd) {
				a2.hd.querySelector("button").className = (aZ.length === 2 && aj === 0) ? "off"
						: ""
			}
			if (M && aj >= 0) {
				M.setActive(c2.currentQuality)
			}
			t.getElementById("hdTxt").innerHTML = aZ[aj].label, aj
		}
		function cX() {
			return (!bf && b8 && b8.length > 1 && an)
		}
		function cR(c2) {
			b8 = c2.tracks;
			if (cX()) {
				s.style(a2.cc, a);
				an.clearOptions();
				for (var c3 = 0; c3 < b8.length; c3++) {
					an.addOption(b8[c3].label, c3)
				}
				I(c2)
			} else {
				s.style(a2.cc, v)
			}
			aF()
		}
		function I(c2) {
			if (!b8) {
				return
			}
			cJ = Math.floor(c2.track);
			if (a2.cc) {
				a2.cc.querySelector("button").className = (b8.length === 2 && cJ === 0) ? "off"
						: ""
			}
			if (an && cJ >= 0) {
				an.setActive(c2.track)
			}
		}
		function aa(c2) {
			if (a2.cast) {
				if (y.canCast()) {
					y.addClass(a2.cast, "jwcancast")
				} else {
					y.removeClass(a2.cast, "jwcancast")
				}
			}
			aC(c2 || cM)
		}
		function aC(c2) {
			cM = c2;
			a0("cast", c2.active);
			aF()
		}
		function aL() {
			return (!!t.querySelector("#" + cF.id + " .jwplaylist") && !cF
					.jwGetFullscreen())
		}
		function cf() {
			bB = y.extend({}, a5, cH.getComponentSettings("controlbar"), aA);
			cm = ag("background").height;
			var c3 = bw ? 0 : bB.margin;
			var c2 = {
				height : cm,
				bottom : c3,
				left : c3,
				right : c3,
				"max-width" : bw ? "" : bB.maxwidth
			};
			s.style(aK, c2);
			s(af(".jwtext"), {
				font : bB.fontsize + "px/" + ag("background").height + "px "
						+ bB.font,
				color : bB.fontcolor,
				"font-weight" : bB.fontweight
			});
			s(af(".jwoverlay"), {
				bottom : cm - 20
			})
		}
		function af(c2) {
			return "#" + cI + (c2 ? " " + c2 : "")
		}
		function ce() {
			return bZ("span")
		}
		function bZ(c2) {
			return t.createElement(c2)
		}
		function at() {
			var c4 = ak("capLeft");
			var c3 = ak("capRight");
			var c2 = ak("background", {
				position : "absolute",
				left : ag("capLeft").width,
				right : ag("capRight").width,
				"background-repeat" : "repeat-x"
			}, true);
			if (c2) {
				cd(aK, c2)
			}
			if (c4) {
				cd(aK, c4)
			}
			bv();
			if (c3) {
				cd(aK, c3)
			}
		}
		function aX(c2, c3) {
			switch (c2.type) {
			case r:
				return bJ(c2.name);
			case c:
				if (c2.name !== "blank") {
					return ao(c2.name, c3)
				}
				break;
			case u:
				return b3(c2.name)
			}
		}
		function ak(c4, c3, c5, c9, c6) {
			var c8 = ce(), da = ag(c4), c2 = c9 ? " left center" : " center", db = x(da), c7;
			c8.className = "jw" + c4;
			c8.innerHTML = "&nbsp;";
			if (!da || !da.src) {
				return
			}
			if (c5) {
				c7 = {
					background : 'url("' + da.src + '") repeat-x ' + c2,
					"background-size" : db,
					height : c6 ? da.height : ""
				}
			} else {
				c7 = {
					background : 'url("' + da.src + '") no-repeat' + c2,
					"background-size" : db,
					width : da.width,
					height : c6 ? da.height : ""
				}
			}
			c8.skin = da;
			s(af((c6 ? ".jwvertical " : "") + ".jw" + c4), y.extend(c7, c3));
			a2[c4] = c8;
			return c8
		}
		function ao(c3, da) {
			if (!ag(c3 + "Button").src) {
				return null
			}
			if (w && (c3 === "mute" || c3.indexOf("volume") === 0)) {
				return null
			}
			if (e && /hd|cc/.test(c3)) {
				return null
			}
			var c6 = ce();
			var db = ce();
			var c5 = ae(cq);
			var c8 = bZ("button");
			c6.className = "jw" + c3;
			c8.style.height = "16px";
			c8.style.bottom = "-11px";
			if (c3 == "hd") {
				c8.id = "hdTxt";
				c8.innerHTML = "¸ßÇå";
				c8.style.color = "#787d82";
				c8.style.padding = "0";
				c8.style.textAlign = "center";
				c8.style.font = "12px Microsoft YaHei,SimSun,Arial";
				c8.onmouseover = function() {
					if (ac == null) {
						c8.style.color = "#f01400";
						cQ();
						bF = true
					}
				};
				c8.onmouseout = function() {
					c8.style.color = "#787d82"
				}
			}
			if (c3 == "speed") {
				c8.id = "speedTxt";
				c8.innerHTML = cp + " X";
				c8.style.color = "#787d82";
				c8.style.padding = "0";
				c8.style.width = "42px";
				c8.style.font = "12px Microsoft YaHei,SimSun,Arial";
				c8.onmouseover = function() {
					if (ac == null) {
						c8.style.color = "#f01400";
						bj();
						M.hide()
					}
				};
				c8.onmouseout = function() {
					c8.style.color = "#787d82";
					cy()
				}
			}
			if (c3 == "set") {
				c8.onmouseover = function() {
					if (ac == null) {
						X();
						M.hide()
					}
				};
				c8.onmouseout = function() {
					cP()
				}
			}
			if (da === "left") {
				cd(c6, db);
				cd(c6, c5)
			} else {
				cd(c6, c5);
				cd(c6, db)
			}
			if (!w) {
				c8.addEventListener("click", O(c3), false)
			} else {
				if (c3 !== "hd" && c3 !== "cc") {
					var c2 = new y.touch(c8);
					c2.addEventListener(y.touchEvents.TAP, O(c3))
				}
			}
			c8.tabIndex = -1;
			c8.setAttribute("type", "button");
			cd(db, c8);
			var c4 = ag(c3 + "Button"), c9 = ag(c3 + "ButtonOver"), dc = ag(c3
					+ "ButtonOff");
			bE(af(".jw" + c3 + " button"), c4, c9, dc);
			var c7 = bb[c3];
			if (c7) {
				bE(af(".jw" + c3 + ".jwtoggle button"), ag(c7 + "Button"),
						ag(c7 + "ButtonOver"))
			}
			if (cb[c3]) {
				y.addClass(c6, "jwtoggle")
			} else {
				y.removeClass(c6, "jwtoggle")
			}
			a2[c3] = c6;
			return c6
		}
		var av = !!navigator.userAgent.match(/Trident\/7.0/);
		var bt = !!navigator.userAgent.match(/.NET4.0E/);
		var bI = av && bt;
		var cC = (navigator.userAgent.match(/MSIE/i) ? true : false);
		var cp = "1.0";
		if (localStorage.currentRate) {
			cp = localStorage.currentRate
		}
		function Z() {
			var c2 = this.innerText ? String(this.innerText)
					: String(this.textContent);
			cp = c2.substring(0, c2.length - 1);
			bD();
			au()
		}
		function au() {
			var c2 = t.querySelector("video");
			if (bI || cC) {
				p().seek(p().getPosition());
				p().onSeek(function() {
					c2.playbackRate = cp
				});
				p().onPause(function() {
					c2.playbackRate = cp
				});
				p().onPlay(function() {
					c2.playbackRate = cp
				});
				c2.playbackRate = Number(cp)
			} else {
				p().seek(p().getPosition());
				c2.playbackRate = Number(cp)
			}
			localStorage.currentRate = cp;
			t.getElementById("speedTxt").innerHTML = cp + " X";
			p().onSpeedChange()
		}
		i.thisCurrentRate = b2;
		function b2() {
			return cp
		}
		var a4, cx;
		function ci() {
			a4 = t.createElement("div");
			cx = t.getElementById(cF.id);
			a4.style.background = "#14191e";
			a4.style.position = "absolute";
			a4.style.opacity = ".9";
			a4.style.font = "14px Microsoft YaHei,SimSun,Arial";
			a4.style.width = "80px";
			a4.style.height = "280px";
			a4.style.bottom = "53px";
			a4.style.zIndex = "991";
			a4.style.color = "#787d82";
			a4.innerHTML = '<ul id="speedAjust" style="cursor:pointer" class="multi-speed"><li><i></i><span>0.5x</span></li>        <li><i></i><span>0.75x</span></li><li><i></i><span>1.0x</span></li><li><i></i><span>1.25x</span></li><li><i></i><span>1.5x</span></li><li><i></i><span>1.75x</span></li><li><i></i><span>2.0x</span></li></ul>';
			cx.appendChild(a4);
			var c2 = t.getElementById("speedAjust")
					.getElementsByTagName("span");
			for (var c4 = 0; c4 < c2.length; c4++) {
				var c3 = c2[c4];
				c3.style.color = "#787d82";
				c3.style.display = "block";
				c3.style.width = "80px";
				c3.style.height = "40px";
				c3.style.textAlign = "center";
				c3.style.padding = "0";
				c3.style.borderTop = "1px solid #363c40";
				c3.style.borderTop.opacity = ".3";
				c3.style.verticalAlign = "middle";
				c3.style.lineHeight = "40px";
				c3.onmouseover = function() {
					var c5 = this.innerText ? String(this.innerText)
							: String(this.textContent);
					if (cp != c5.substring(0, c5.length - 1)) {
						this.style.color = "#fff"
					}
					this.style.backgroundColor = "#363c40";
					this.style.backgroundColor.opacity = ".3"
				};
				c3.onmouseout = function() {
					var c5 = this.innerText ? String(this.innerText)
							: String(this.textContent);
					if (cp != c5.substring(0, c5.length - 1)) {
						this.style.color = "#787d82"
					} else {
						this.style.color = "#f01400"
					}
					this.style.backgroundColor = "transparent"
				};
				bD();
				c3.addEventListener("click", Z)
			}
			a4.style.visibility = "hidden";
			a4.onmouseover = function() {
				bj()
			};
			a4.onmouseout = function() {
				a4.style.visibility = "hidden"
			}
		}
		function bD() {
			var c2 = t.getElementById("speedAjust")
					.getElementsByTagName("span");
			for (var c5 = 0; c5 < c2.length; c5++) {
				var c4 = c2[c5];
				c4.style.color = "#787d82";
				var c3 = c4.innerText ? String(c4.innerText)
						: String(c4.textContent);
				if ((cp) == c3.substring(0, c3.length - 1)) {
					c4.style.color = "#f01400"
				}
			}
		}
		var cT, bg;
		var aV = y.getCookies(), E = aV.playAuto;
		if (E == "undefined") {
			E = false
		}
		function aI() {
			if (l == g || l == null) {
				l = true
			}
			cT = t.createElement("div");
			cT.id = "setoverlay";
			var c8 = t.getElementById(cF.id);
			cT.style.position = "absolute";
			cT.style.zIndex = "991";
			cT.style.bottom = "48px";
			var c2 = new Image();
			if (l == false || l == "false") {
				c2.src = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAQQAAABiCAYAAAC20q35AAAF2klEQVR4nO3cwU8aaRiA8ddVAhmxVKjjhVrJEmLqScV7TeSMV7zjHyX3epWzJu5dSk82DZnNsI2Hii2tEScQ3XQP5H07nUV3N0uz1X1+l5pBBkryPd8339BOZOafvhcAEJGf/us3AODHQRAAGIIAwBAEAIYgADAEAYAhCAAMQQBgCAIAQxAAGIIAwBAEAIYgADAEAYCZGvcJ84V8fOPFxuPo8aNfjj57LW9Q2a64H87PByIiBweHFyIi5a1y+uTNyZXX8gbh51S2K+7ey71O9FxrxRUnm11I1Pfr3cp2xU0mkxOj3ou+pr7G6em7/qvG60Afv+t1w+dsNhuX6dlMbDGXS+ixXq/3ZdR7A+6z77JC6HQ617Xd2lmv1/uif4YfPzg4vHgyNxfPF/JxEZH6fr07KiKjVLYr7upqccZ13Vh1pzqvg7K2Wztr+36/2Wxctn2/3/b9fnig1/fr3Wx2IRE+l+M4U9EYqNpu7UzPqcfavt/X43//0wDuj4lx/wcpukLodDrXjuNMiYgkk8mJ8Aph1MyaL+Tjy8+Xp+v79a4eq+5U50cNPl0hnLw5uborJJ1O51pExHXdWPSxtu/3wzO+yNdZnxUC/q++SxB0YOvgr2xX3CAIbqIDs9PpXIcDEPVXQQg/Vy9FnszNxYMguAk/tlZccdKzmZheomi02r7fPzg4vBgVo7BSaTMlMlzZVHeq8+FLEeAhGfsegshwRq7uVOdFhoNaROS4cXxV368PooNcZ+Pb4pAv5OO3DT7dF1hdLc7UdmtnpdJmqtV629PXvW0Wzz1bTIQvBVKPZiaDq6vfRYaDP7pyUNWdakJEZOPFxuONFxusEvDgjD0IXssbeC3vTOTuTUHd3Nt7udfRGT/6OyLDwRsNQqGwlNSIrK4WZ0S+hic8mDUOelwHdPjyRUQkPZuJdT99vBaxjc4LfV51pzrf9v2+Mz09KTLci/gXHw/wQxt7ENaKK44OUpGvA/Xol6PPqUczk71e70t6NvOna/qobHYh0Ww2LguFpWT4eHmrnP5wfj4IgmCyvl/vlkqbqe6nj9evGq+D6M/6nPRsJqaXB+WtclqPB0Fwky/k44u5XCJ8xyN8aRNezawVVxz9+4iwj4CHZ+xBeNV4HejsH10hlLfK6VbrbS86yFW+kI+vF9dTey/3Oq7rxvTOQPiyob5f764VVxydsUVEVleLMxqhxVwuoT/rrB/9WZ2evuvrXoIei97KrO5U55vNxqW+jv4eMcBDNPYgRK/BdUZt+35fB3l6NhMrlTZTOiuLDPcdXNd9XNutnZW3ymkdpCdvTq7Wi+spr+XdOviazcblXSuEJ3Nzcf+3dl/k21uNd61UdGUQPo+uMkSGsfvnnw7wYxt7EA4ODi8q25V4EAQ3p6fv+tnsQsJxnKnFXC6hM63u1ucL+b7X8gbp2UxMNxV1Sa8Dz2t5g9yzxVtvV4rcvUJYK644QRDcaAR05i9vldOO40zVdmtnle2KGw1U+NJAVxfhfYjodyuAh2Dstx1151+/J+C1vMGo24RrxRWnUFhKhgd5qbSZcqanJ0dt3OljIsPVhM7W4VVB9Pe7nz5eFwpLyePG8cXy8+Vp13Vj+t0EkW83CDVE4dul4fOIDFcU4RUClwx4aMYeBAD3F/+4CYAhCAAMQQBgCAIAQxAAGIIAwBAEAIYgADAEAYAhCAAMQQBgCAIAQxAAGIIAwBAEAIYgADAEAYAhCAAMQQBgCAIAQxAAGIIAwBAEAIYgADAEAYAhCAAMQQBgCAIAQxAAGIIAwBAEAIYgADAEAYAhCAAMQQBgCAIAQxAAGIIAwBAEAIYgADAEAYAhCAAMQQBgCAIAQxAAGIIAwBAEAIYgADAEAYAhCAAMQQBgCAIAQxAAGIIAwBAEAIYgADAEAYAhCAAMQQBgCAIAQxAAGIIAwEyN+4SF/M9jPyeA0VrerzfjPN9EZv7p+3GeEMD9xSUDAEMQABiCAMAQBACGIAAwBAGAIQgAzB/U5PaOMawscgAAAABJRU5ErkJggg=="
			} else {
				c2.src = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAQQAAACMCAYAAACanNcLAAAK6klEQVR4nO3dT0xbVxbH8cuAZcuGGEyxFyGp0VhW1DSa8CebthRCwq4RmUiJBItIJTI7VMHsGw37tELdBTVILBopkQiI7EgTGJrZhJiM0lQV8shuJ5WK05q4gGULKLNw7sntqw2BmhbT72cT85793rOl+7vn3GdISbXv0PcKAJRSf/mjLwDA3kEgABAEAgBBIAAQBAIAQSAAEAQCAEEgABAEAgBBIAAQBAIAQSAAEAQCAEEgABBlhT5gIBiwn2w9WWndfm/q3vPIfCTT2dXp/eHZs4xSSk1O3kkqpVTH2Q7Pk6+erETmIxnzNZ1dnd7rn12PW4/V2FTvrK097BgfG090dnV6y8vLS3Jdiz6nPsfTp9+mH87OpfT+zc5rHjMcnl3yVFXb/HV1Dr1teXl5I9e1AcVsVyqEeDy+OnR1aGF5eXlD/2vun5y8k3ytpsYeCAbsSik1PjaeyBUiuXR2dXobGpoqvF6vLdQT8ulBOXR1aCEWjabD4dmlWDSajkWjaXOgj4+NJ2prDzvMYzmdzjJrGGhDV4cW9DH1tlg0mtbbX/3TAIpHSaH/QIquEOLx+KrT6SxTSqny8vISs0LINbMGggH70TeOusbHxhN6W6gn5Ms1+HSF8OSrJyubBUk8Hl9VSimv12uz7otFo2lzxlfq5axPhYA/q10JBD2w9eDv7Or0plKpNevAjMfjq2YAWG0VCOZrdSvyWk2NPZVKrZn7GpvqnZ6qaptuUXRoxaLR9OTknWSuMDK1t592K5WtbEI9IZ/ZigD7ScHXEJTKzsihnpBPqeygVkqpB7MPVsbHxjPWQa5n43zhEAgG7PkGn14XaGhoqhi6OrTQ3n7aPT//9bI+b75ZvO51v8NsBdwHKkpTKyvrSmUHv7Vy0EI9IYdSSp1sPVl5svUkVQL2nYIHQmQ+konMRxaU2nxRUC/uXf/selzP+NbnKJUdvNZACAaPlOsQaWhoqlDqZfCYg1mHg96uB7TZviillKeq2pZY/HFVKVnoTOrXhXpCvlg0mna6XKVKZdcifsPHA+xpBQ+ExqZ6px6kSr0cqPem7j13H6goXV5e3vBUVf+qp7eqrT3sCIdnl4LBI+Xm9o6zHZ4fnj3LpFKp0vGx8UR7+2l3YvHH1YezcynrY/0aT1W1TbcHHWc7PHp7KpVaCwQDdn9dncO842G2NmY109hU79TvRynWEbD/FDwQHs7OpfTsb60QOs52eObnv162DnItEAzYTzSdcF//7Hrc6/Xa9J0Bs20YHxtPNDbVO/WMrZRSDQ1NFTqE/HV1Dv1Yz/rWx9rTp9+m9VqC3ma9lRnqCfnC4dklfR79PMIA+1HBA8Hag+sZNRaNpvUg91RV29rbT7v1rKxUdt3B6/VWDl0dWug42+HRg/TJV09WTjSdcEfmI3kHXzg8u7RZhfBaTY09+k0srdQvbzVuVqnoysA8jq4ylMqG3fY/HWBvK3ggTE7eSXZ2ddpTqdTa06ffpmtrDzucTmeZv67OoWdavVofCAbSkflIxlNVbdOLirqk1wMvMh/J1L3uz3u7UqnNK4TGpnpnKpVa0yGgZ/6Osx0ep9NZNnR1aKGzq9NrDSizNdDVhbkOYf1uBbAfFPy2o175198TiMxHMrluEzY21TuDwSPl5iBvbz/tdrpcpbkW7vQ+pbLVhJ6tzarA+vzE4o+rweCR8gezD5JH3zjq8nq9Nv3dBKV+uUCog8i8XWoeR6lsRWFWCLQM2G8KHggAihe/3ARAEAgABIEAQBAIAASBAEAQCAAEgQBAEAgABIEAQBAIAASBAEAQCAAEgQBAEAgABIEAQBAIAASBAEAQCAAEgQBAEAgABIEAQBAIAASBAEAQCAAEgQBAEAgABIEAQBAIAASBAEAQCAAEgQBAEAgABIEAQBAIAASBAEAQCAAEgQBAEAgABIEAQBAIAASBAEAQCAAEgQBAEAgABIEAQBAIAASBAEAQCAAEgQBAEAgABIEAQBAIAASBAEAQCAAEgQBAEAgABIEAQBAIAASBAEAQCAAEgQBAEAgARFmhDxgM/LXgxwSQ23zkv2uFPF5Jte/Q94U8IIDiRcsAQBAIAASBAEAQCAAEgQBAEAgABIEAQBAIAASBAEAQCAAEgQBAEAgABIEAQBAIAASBAEAQCAAEgQBA7NqfO/tbY4PH5XLatvu6lZXU6n8ehhO7cU0ANrdrgeByOW3//tcXC9t93VvvvuPb6TkHBi57lFLqww//ueNAaW1ptv/93Fn3Bx/8I55r/8jIsO/ixffzvq+RkWG5fvN55y+cc77z9tvl1uP29/W6K6s8pdZrHhi47PH7/dsK1Fgstvpb3rtpZGTYd+3Ta8+npmcySm39uWB/KOo/iHr+wjnnmffOVFi3m4NSm7g9sXTzxmhKKaUGB694q6o8Jeb+7Q6m1pZme/el7spc+yZuTywde/OYw9x288Zo6tibxxz9fb3ujz7+JKm3V1Z5Sp8vJtatx8h1Lfr9bhZIO5XrM+m+1F3Zfan7F88zP9tCBhD2hqIOBKWUWlxMbGw1aw0OXvGaP+9kljNn7JGRYd+juXB64vbEkh6g5y+ccx5785jj8ZeP07kqAaWUuvv53RU94yqVDRW/32+79vndle1eT6FZr5cK4c+p6AOhUKzVhjkTPpoLp/VMqFuG/r5et36++VwzNBYXExvJ5E9rZulvzrixWGz1xbZK6/Y/eua1ViFT0zOZqekZwmCfK/pAqKrylORqEbbr5o3R1M0boylrr6/XJaxelP1JpbKDX7cJfr/f9mgunDbbAv0cc5DplsMc/P19vW7j2HnpiqfQs/V2PkezesD+UfSBsN2WId+6g1LZ3v9Vztnf1+v+33ffrR46eNB2vL7BoVsGpbIza39fr1tXCPmure1Um+vRXDj9ut9v19sqqzylj798nN7q/LdGx5Jtp9pcOoj02oh+r9a1gK3o6zQDa7NwsrZg2D+KPhC2S1cC+Xri/r5edzL5k/xvOG73gTI9SM2BkK+9MB/fGh37VZWgH+sZdnDwire1pdk+NT2T8fv9truvsJ7wonzP6CrDrGh+jx5/u4GD4lH0gbDTlqHtVJvri/v3l63b8636Dwxc9nxx//7ymffOVOh2YXDwivfW6FhyanomoxcVdflvhkdrS7NdqZd9+cjIsE+X29/EYpm2U22uhobjjlgstrqdMvxFMCwMDFz2DAxc9vye6w60C/tT0QfCTu4ytLY0293uA2W51gzyrfrrwWZtN6wLgrnCqcZXU7q4mNjIdW0fffxJcmRk2Kf8ftu1T6893+x95PNHL0Bi/yj6QNiJtlNtrlujY8nWlma7HuB6hrfO0luVx7r036xCOHTwoO2bWExu35mvN38uhln3/IVzznzhhuJX9IGw3ZZB3/vvvtRdubiY2Lh48f0F80tGub7082zh2a9aCG2rCqG1pdl+vL7BYc7+ekDp7zZM3J5YOnTwoC3XIuFeYP3W5KsuvqL4FH0gbLdl0H23uX9qeibTfalb6UHb39frPl7f4NDHn5qeyehBYZ0d891+0+dsO9Xmmrg9sVTjqynVYfFoLpzW+y0BlBwZGfaZlcZesJeuBbtr1/47+Lfefce3099l2MnrAPx2u1YhrK+v/7yTX1RaX1//eTeuB8DWdq1CAFB8+AMpAASBAEAQCAAEgQBAEAgABIEAQBAIAASBAEAQCAAEgQBAEAgABIEAQBAIAASBAEAQCAAEgQBA/B9Uq6PhMoStwAAAAABJRU5ErkJggg=="
			}
			cT.appendChild(c2);
			var c6 = t.createElement("input");
			c6.type = "button";
			c6.value = "HTML5";
			c6.style.width = "100px";
			c6.style.height = "30px";
			c6.style.align = "center";
			c6.style.font = "14px Microsoft YaHei,SimSun,Arial";
			c6.style.position = "absolute";
			c6.style.marginLeft = "-240px";
			c6.style.marginTop = "40px";
			c6.style.background = "#f01400";
			c6.style.border = "1px solid #f01400";
			c6.style.color = "white";
			cT.appendChild(c6);
			var c9 = t.createElement("input");
			c9.id = "flashBtn";
			c9.type = "button";
			c9.value = "FLASH";
			c9.style.position = "absolute";
			c9.style.width = "100px";
			c9.style.height = "30px";
			c9.style.align = "center";
			c9.style.marginLeft = "-120px";
			c9.style.marginTop = "40px";
			c9.style.background = "#14191e";
			c9.style.backgroundColor.opacity = "0";
			var c7 = y.isSafari();
			if (y.flashVersion() == 0) {
				c9.style.border = "1px solid #4D5559";
				c9.style.color = "#4D5559"
			} else {
				c9.style.cursor = "pointer";
				c9.style.border = "1px solid #4D5559";
				c9.style.color = "#B5B9BC";
				c9.onmouseover = function() {
					this.style.border = "1px solid #7D888c";
					this.style.color = "#fff"
				};
				c9.onmouseout = function() {
					this.style.border = "1px solid #4D5559";
					this.style.color = "#B5B9BC"
				};
				c9.addEventListener("click", c4)
			}
			function c4() {
				i.switchjwplayer("flash")
			}
			cT.appendChild(c9);
			if (l || l == g) {
				var c5 = t.createElement("div");
				c5.style.marginLeft = "24px";
				c5.style.marginTop = "-40px";
				c5.style.width = "20px";
				c5.style.height = "20px";
				c5.style.position = "absolute";
				c5.style.cursor = "pointer";
				c5.style.display = "block";
				c5.type = "button";
				c5.style.background = "rgba(0,0,0,0)";
				cT.appendChild(c5);
				cT.appendChild(c5);
				var c3 = new Image();
				c3.id = "imgid";
				c3.src = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAwAAAAJCAYAAAAGuM1UAAAAt0lEQVQYlY3QsU3DYBDF8d85KejiDRghHiH0SDBCikh8HYzABoSOSBRmAxcZwBtgRsgGbpGIvhSYYEBIuep0+r+ndy+cOhsP6CcnwU9uhXuU4dk5WNn9A18JDXoTVeFDa687Cn/Cc6EG2cLKrsAapb3GxmyUeTbAJZaSNygkj7IaFYPbp2ODSla78fJ1jpHj6yBay0phKWslF+OU09G+kHXC3WDTCde/3/qudevdpRZn6JGkv80dACOiLYjVNwICAAAAAElFTkSuQmCC";
				c5.appendChild(c3);
				c3.style.visibility = "hidden";
				cT.onmouseover = function() {
					X()
				};
				cT.onmouseout = function() {
					this.style.visibility = "hidden";
					var da = t.getElementById("imgid");
					da.style.visibility = "hidden"
				};
				cT.style.visibility = "hidden";
				cT.appendChild(c5);
				c8.appendChild(cT);
				c5.addEventListener("click", cN)
			} else {
				cT.onmouseover = function() {
					X()
				};
				cT.onmouseout = function() {
					this.style.visibility = "hidden"
				};
				cT.style.visibility = "hidden";
				c8.appendChild(cT)
			}
		}
		function cN() {
			if (l || l == g) {
				var c2 = t.getElementById("imgid");
				if (c2.style.visibility == "hidden") {
					E = true;
					y.saveCookie("playAuto", true);
					c2.style.visibility = "visible"
				} else {
					E = false;
					y.saveCookie("playAuto", false);
					c2.style.visibility = "hidden"
				}
			}
		}
		function b4() {
			clearTimeout(bg);
			cT.style.visibility = "hidden";
			if (l || l == g) {
				var c2 = t.getElementById("imgid");
				c2.style.visibility = "hidden"
			}
		}
		function cP() {
			clearTimeout(bg);
			bg = setTimeout(b4, 500)
		}
		function X() {
			aB = y.bounds(aK);
			var c2 = aB.width - 280;
			clearTimeout(bg);
			cT.style.visibility = "visible";
			cT.style.left = c2 + "px";
			if (l || l == g) {
				var c3 = t.getElementById("imgid");
				if (E == true || E == "true") {
					c3.style.visibility = "visible"
				} else {
					c3.style.visibility = "hidden"
				}
			}
		}
		var a3;
		function cQ() {
			clearTimeout(a3);
			a4.style.visibility = "hidden"
		}
		function cy() {
			clearTimeout(a3);
			a3 = setTimeout(cQ, 500)
		}
		function bj() {
			aB = y.bounds(aK);
			var c2 = aB.width - 218;
			clearTimeout(a3);
			a4.style.visibility = "visible";
			a4.style.left = c2 + "px"
		}
		function bE(c2, c3, c4, c5) {
			if (!c3 || !c3.src) {
				return
			}
			s(c2, {
				width : c3.width,
				background : "url(" + c3.src + ") no-repeat center",
				"background-size" : x(c3)
			});
			if (c4.src && !w) {
				s(c2 + ":hover," + c2 + ".off:hover", {
					background : "url(" + c4.src + ") no-repeat center",
					"background-size" : x(c4)
				})
			}
			if (c5 && c5.src) {
				s(c2 + ".off", {
					background : "url(" + c5.src + ") no-repeat center",
					"background-size" : x(c5)
				})
			}
		}
		function O(c2) {
			return function(c3) {
				if (ar[c2]) {
					ar[c2]();
					if (w) {
						bK.sendEvent(b.JWPLAYER_USER_ACTION)
					}
				}
				if (c3.preventDefault) {
					c3.preventDefault()
				}
			}
		}
		var aQ, bC, b5;
		firstPlay = true;
		function cE() {
			aQ = t.createElement("div");
			bC = t.getElementById(cF.id);
			aQ.style.background = "#333333";
			aQ.style.color = "#fff";
			aQ.style.position = "absolute";
			aQ.style.textAlign = "center";
			aQ.style.lineHeight = "30px";
			aQ.style.font = "14px/2em Microsoft YaHei,SimSun,Arial";
			aQ.style.width = "80px";
			aQ.style.height = "30px";
			aQ.style.left = "47%";
			aQ.style.top = "75%";
			aQ.style.zIndex = "999";
			aQ.style.visibility = "hidden";
			aQ.innerHTML = "²¥·Å";
			bC.appendChild(aQ)
		}
		function bs() {
			aQ.style.visibility = "hidden"
		}
		function b7(c2) {
			if (firstPlay) {
				firstPlay = false;
				return
			}
			aQ.innerHTML = c2;
			aQ.style.visibility = "visible";
			clearTimeout(b5);
			b5 = setTimeout(bs, 3000);
			if (!bK.visible) {
			}
		}
		function aW() {
			if (cb.play) {
				cF.jwPause()
			} else {
				cF.jwPlay()
			}
		}
		function bN() {
			var c2 = !cb.mute;
			cF.jwSetMute(c2);
			if (!c2 && bz === 0) {
				cF.jwSetVolume(50)
			}
			aD()
		}
		function J(c2) {
			y.foreach(bk, function(c4, c3) {
				if (c4 !== c2) {
					if (c4 === "cc") {
						cA()
					}
					if (c4 === "hd") {
						cV()
					}
					c3.hide()
				}
			})
		}
		function am(c2) {
			if (!aK || !a2.alt) {
				return
			}
			if (c2 === g) {
				c2 = (aK.parentNode && aK.parentNode.clientWidth >= 320)
			}
			if (c2 && !bf) {
				s.style(bX, a)
			} else {
				s.style(bX, v)
			}
		}
		var aU = false;
		function D() {
			if (!aU) {
				return
			}
			if (bw || bf) {
				return
			}
			s.block(cI);
			ba.show();
			V("volume", ba);
			J("volume")
		}
		function ck(c2) {
			aM(c2);
			if (c2 < 0.1) {
				c2 = 0
			}
			if (c2 > 0.9) {
				c2 = 1
			}
			cF.jwSetVolume(c2 * 100)
		}
		function aJ(c3) {
			var c2;
			if (F) {
				c3 = F.position;
				if (c3.toString().slice(-1) === "%") {
					c2 = cu * parseFloat(c3.slice(0, -1)) / 100
				} else {
					c2 = parseFloat(c3)
				}
			} else {
				c2 = c3 * cu
			}
			cF.jwSeek(c2)
		}
		function ap() {
			cF.jwSetFullscreen()
		}
		function bO() {
			cF.jwPlaylistNext()
		}
		function cY() {
			cF.jwPlaylistPrev()
		}
		function a0(c2, c3) {
			if (!C.isBoolean(c3)) {
				c3 = !cb[c2]
			}
			if (a2[c2]) {
				if (c3) {
					y.addClass(a2[c2], "jwtoggle")
				} else {
					y.removeClass(a2[c2], "jwtoggle")
				}
				y.addClass(a2[c2], "jwtoggling");
				setTimeout(function() {
					y.removeClass(a2[c2], "jwtoggling")
				}, 100)
			}
			cb[c2] = c3
		}
		function bJ(c3) {
			var c6 = {}, c2 = (c3 === "alt") ? "elapsed" : c3, c5 = ag(c2
					+ "Background");
			if (c5.src) {
				var c4 = ce();
				c4.id = d(cI, c3);
				if (c3 === "elapsed" || c3 === "duration") {
					c4.className = "jwtext jw" + c3 + " jwhidden";
					bX.push(c4)
				} else {
					c4.className = "jwtext jw" + c3
				}
				c6.background = "url(" + c5.src + ") repeat-x center";
				c6["background-size"] = x(ag("background"));
				s.style(c4, c6);
				c4.innerHTML = (c3 !== "alt") ? "00:00" : "";
				a2[c3] = c4;
				return c4
			}
			return null
		}
		function ae(c3) {
			var c2 = ak(c3.name);
			if (!c2) {
				c2 = ce();
				c2.className = "jwblankDivider";
				c2.style.marginRight = "15px";
				c2.style.marginLeft = "15px";
				c2.style.height = "16px";
				c2.style.bottom = "-12px"
			}
			if (c3.className) {
				c2.className += " " + c3.className
			}
			return c2
		}
		var bF = false;
		function bA() {
			if (!bF) {
				return
			}
			if (aZ.length > 2) {
				if (U) {
					clearTimeout(U);
					U = g
				}
				s.block(cI);
				M.show();
				V("hd", M);
				J("hd")
			}
		}
		function cZ() {
			if (b8 && b8.length > 2) {
				if (cU) {
					clearTimeout(cU);
					cU = g
				}
				s.block(cI);
				an.show();
				V("cc", an);
				J("cc")
			}
		}
		function H(c2) {
			if (c2 >= 0 && c2 < aZ.length) {
				cF.jwSetCurrentQuality(c2);
				cV();
				M.hide()
			}
		}
		function bH(c2) {
			if (c2 >= 0 && c2 < b8.length) {
				cF.jwSetCurrentCaptions(c2);
				cA();
				an.hide()
			}
		}
		function a6() {
			if (b8.length !== 2) {
				return
			}
			bH((cJ + 1) % 2)
		}
		function cW() {
			if (aZ.length !== 2) {
				return
			}
			H((aj + 1) % 2)
		}
		function b0() {
			if (cM.active) {
				cF.jwOpenExtension()
			} else {
				cF.jwStartCasting()
			}
		}
		function b3(c2) {
			if (w && c2.indexOf("volume") === 0) {
				return
			}
			var c4 = ce(), c7 = c2 === "volume", c5 = c2
					+ (c2 === "time" ? "Slider" : ""), da = c5 + "Cap", c6 = c7 ? "Top"
					: "Left", dc = c7 ? "Bottom" : "Right", c8 = ak(da + c6,
					null, false, false, c7), c9 = ak(da + dc, null, false,
					false, c7), c3 = cg(c2, c7, c6, dc), dd = ag(da + c6), db = ag(da
					+ c6);
			c4.className = "jwslider jw" + c2;
			if (c8) {
				cd(c4, c8)
			}
			cd(c4, c3);
			if (c9) {
				if (c7) {
					c9.className += " jwcapBottom"
				}
				cd(c4, c9)
			}
			s(af(".jw" + c2 + " .jwrail"), {
				left : c7 ? "" : dd.width,
				right : c7 ? "" : db.width,
				top : c7 ? dd.height : "",
				bottom : c7 ? db.height : "",
				width : c7 ? "100%" : "",
				height : c7 ? "auto" : ""
			});
			a2[c2] = c4;
			c4.vertical = c7;
			if (c2 === "time") {
				b9 = new j.overlay(cI + "_timetooltip", cH);
				co = new j.thumbs(cI + "_thumb");
				a1 = bZ("div");
				a1.className = "jwoverlaytext";
				be = bZ("div");
				cd(be, co.element());
				cd(be, a1);
				b9.setContents(be);
				bV = c3;
				ca(0);
				cd(c3, b9.element());
				R(c4);
				Y(0);
				a9(0)
			} else {
				if (c2.indexOf("volume") === 0) {
					bo(c4, c7, c6, dc)
				}
			}
			return c4
		}
		var W;
		function cg(dl, c3, c7, dj) {
			var c4 = ce(), dc = [ "Rail", "Buffer", "Progress" ], c8, df;
			c4.className = "jwrail";
			for (var de = 0; de < dc.length; de++) {
				df = (dl === "time" ? "Slider" : "");
				var dd = dl + df + dc[de], c6 = ak(dd, null, !c3, (dl
						.indexOf("volume") === 0), c3), c9 = ak(
						dd + "Cap" + c7, null, false, false, c3), da = ak(dd
						+ "Cap" + dj, null, false, false, c3), c5 = ag(dd
						+ "Cap" + c7), c2 = ag(dd + "Cap" + dj);
				if (c6) {
					var db = ce();
					db.className = "jwrailgroup " + dc[de];
					if (c9) {
						cd(db, c9)
					}
					cd(db, c6);
					if (da) {
						cd(db, da);
						da.className += " jwcap" + (c3 ? "Bottom" : "Right")
					}
					s(af(".jwrailgroup." + dc[de]), {
						"min-width" : (c3 ? "" : c5.width + c2.width)
					});
					db.capSize = c3 ? c5.height + c2.height : c5.width
							+ c2.width;
					s(af("." + c6.className), {
						left : c3 ? "" : c5.width,
						right : c3 ? "" : c2.width,
						top : c3 ? c5.height : "",
						bottom : c3 ? c2.height : "",
						height : c3 ? "auto" : ""
					});
					if (de === 2) {
						c8 = db
					}
					if (de === 2 && !c3) {
						var dk = ce();
						dk.className = "jwprogressOverflow";
						cd(dk, db);
						a2[dd] = dk;
						cd(c4, dk)
					} else {
						a2[dd] = db;
						cd(c4, db)
					}
				}
			}
			var dh = ak(dl + df + "Thumb", null, false, false, c3);
			if (dh) {
				s(af("." + dh.className), {
					opacity : dl === "time" ? 0 : 1,
					"margin-top" : c3 ? dh.skin.height / -2 : ""
				});
				if (dh.className == "jwvolumeHThumb") {
					s(af("." + dh.className), {
						width : "15px"
					})
				}
				dh.className += " jwthumb";
				cd(c3 && c8 ? c8 : c4, dh)
			}
			if (!w) {
				var di = dl;
				if (di === "volume" && !c3) {
					di += "H"
				}
				c4.addEventListener("mousedown", cB(di), false)
			} else {
				var dg = new y.touch(c4);
				dg.addEventListener(y.touchEvents.DRAG_START, ai);
				dg.addEventListener(y.touchEvents.DRAG, cv);
				dg.addEventListener(y.touchEvents.DRAG_END, cv);
				dg.addEventListener(y.touchEvents.TAP, aH)
			}
			if (dl === "time" && !w) {
				W = dh;
				W.style.visibility = "hidden";
				c4.addEventListener("mousemove", cw, false);
				c4.addEventListener("mouseout", T, false)
			}
			a2[dl + "Rail"] = c4;
			return c4
		}
		function K() {
			var c2 = cF.jwGetState();
			return (c2 === f.IDLE)
		}
		function cz(c2) {
			c2.preventDefault();
			t.onselectstart = function() {
				return false
			}
		}
		function aP(c2) {
			S();
			ac = c2;
			i.addEventListener("mouseup", cn, false);
			i.addEventListener("mousemove", cn, false)
		}
		function S() {
			i.removeEventListener("mouseup", cn);
			i.removeEventListener("mousemove", cn);
			ac = null
		}
		function ai() {
			a2.timeRail.className = "jwrail";
			if (!K()) {
				cF.jwSeekDrag(true);
				aP("time");
				cw();
				bK.sendEvent(b.JWPLAYER_USER_ACTION)
			}
		}
		function cv(c2) {
			if (!ac) {
				return
			}
			var c5 = a2[ac].querySelector(".jwrail"), c6 = y.bounds(c5), c4 = c2.x
					/ c6.width;
			if (c4 > 100) {
				c4 = 100
			}
			if (c2.type === y.touchEvents.DRAG_END) {
				cF.jwSeekDrag(false);
				a2.timeRail.className = "jwrail";
				S();
				aG.time(c4);
				T();
				bK.sendEvent(b.JWPLAYER_USER_ACTION)
			} else {
				Y(c4);
				var c3 = (new Date()).getTime();
				if (c3 - cc > 500) {
					cc = c3;
					aG.time(c4)
				}
				bK.sendEvent(b.JWPLAYER_USER_ACTION)
			}
		}
		function aH(c2) {
			var c4 = a2.time.querySelector(".jwrail"), c5 = y.bounds(c4), c3 = c2.x
					/ c5.width;
			if (c3 > 100) {
				c3 = 100
			}
			if (!K()) {
				aG.time(c3);
				bK.sendEvent(b.JWPLAYER_USER_ACTION)
			}
		}
		function cB(c2) {
			return function(c3) {
				if (c3.button) {
					return
				}
				a2[c2 + "Rail"].className = "jwrail";
				if (c2 === "time") {
					if (!K()) {
						cF.jwSeekDrag(true);
						aP(c2)
					}
				} else {
					aP(c2)
				}
			}
		}
		function cn(c2) {
			if (!ac || c2.button) {
				return
			}
			var c6 = a2[ac].querySelector(".jwrail"), c7 = y.bounds(c6), c3 = ac, c5;
			if (az()) {
				c5 = a2[c3].vertical ? ((c7.bottom * 100 - c2.pageY) / (c7.height * 100))
						: ((c2.pageX - (c7.left * 100)) / (c7.width * 100))
			} else {
				c5 = a2[c3].vertical ? ((c7.bottom - c2.pageY) / c7.height)
						: ((c2.pageX - c7.left) / c7.width)
			}
			if (c2.type === "mouseup") {
				if (c3 === "time") {
					cF.jwSeekDrag(false)
				}
				a2[c3 + "Rail"].className = "jwrail";
				S();
				aG[c3.replace("H", "")](c5)
			} else {
				if (ac === "time") {
					Y(c5)
				} else {
					aM(c5)
				}
				var c4 = (new Date()).getTime();
				if (c4 - cc > 500) {
					cc = c4;
					aG[ac.replace("H", "")](c5)
				}
			}
			return false
		}
		function cw(c2) {
			if (c2) {
				br.apply(this, arguments)
			}
			if (b9 && cu && !bw && !w) {
				s.block(cI);
				b9.show();
				V("time", b9)
			}
		}
		function T() {
			if (b9) {
				b9.hide()
			}
		}
		function br(c3) {
			aB = y.bounds(aK);
			N = y.bounds(bV);
			if (!N || N.width === 0) {
				return
			}
			var c2, c4;
			if (az()) {
				c2 = (c3.pageX ? (c3.pageX - N.left * 100) : c3.x);
				c4 = N.width * 100
			} else {
				c2 = (c3.pageX ? (c3.pageX - N.left) : c3.x);
				c4 = N.width
			}
			b9.positionX(Math.round(c2));
			ca(cu * c2 / c4)
		}
		var ca = (function() {
			var c3;
			var c2 = function(c4) {
				s.style(b9.element(), {
					width : c4
				});
				V("time", b9)
			};
			return function(c4) {
				var c6 = co.updateTimeline(c4, c2);
				var c5;
				if (F) {
					c5 = F.text;
					if (c5 && (c5 !== c3)) {
						c3 = c5;
						s.style(b9.element(), {
							width : (c5.length > 32) ? 160 : ""
						})
					}
				} else {
					c5 = y.timeFormat(c4);
					if (!c6) {
						s.style(b9.element(), {
							width : ""
						})
					}
				}
				if (a1.innerHTML !== c5) {
					a1.innerHTML = c5
				}
				V("time", b9)
			}
		})();
		function R() {
			if (!a2.timeSliderRail) {
				s.style(a2.time, v)
			}
			if (a2.timeSliderThumb) {
				s.style(a2.timeSliderThumb, {
					"margin-left" : (ag("timeSliderThumb").width / -2)
				})
			} else {
			}
			var c4 = "timeSliderCue", c2 = ag(c4), c3 = {
				"z-index" : 1
			};
			if (c2 && c2.src) {
				ak(c4);
				c3["margin-left"] = c2.width / -2
			} else {
				c3.display = "none"
			}
			s(af(".jw" + c4), c3);
			a9(0);
			Y(0)
		}
		function bm(c6, c5) {
			if (/^[\d\.]+%?$/.test(c6.toString())) {
				var c3 = ak("timeSliderCue"), c4 = a2.timeSliderRail, c2 = {
					position : c6,
					text : c5,
					element : c3
				};
				if (c3 && c4) {
					c4.appendChild(c3);
					c3.addEventListener("mouseover", function() {
						F = c2
					}, false);
					c3.addEventListener("mouseout", function() {
						F = null
					}, false);
					bP.push(c2)
				}
			}
			bd()
		}
		function bd() {
			y.foreach(bP, function(c3, c2) {
				var c4 = {};
				if (c2.position.toString().slice(-1) === "%") {
					c4.left = c2.position
				} else {
					if (cu > 0) {
						c4.left = (100 * c2.position / cu).toFixed(2) + "%";
						c4.display = null
					} else {
						c4.left = 0;
						c4.display = "none"
					}
				}
				s.style(c2.element, c4)
			})
		}
		function ay() {
			var c2 = a2.timeSliderRail;
			y.foreach(bP, function(c4, c3) {
				c2.removeChild(c3.element)
			});
			bP.length = 0
		}
		bK.setText = function(c4) {
			s.block(cI);
			var c2 = a2.alt, c3 = a2.time;
			if (!a2.timeSliderRail) {
				s.style(c3, v)
			} else {
				s.style(c3, c4 ? v : k)
			}
			if (c2) {
				s.style(c2, c4 ? k : v);
				c2.innerHTML = c4 || ""
			}
			aF()
		};
		function bo(c4, c2, c7, c3) {
			var c5 = "volume" + (c2 ? "" : "H"), c6 = c2 ? "vertical"
					: "horizontal";
			s(af(".jw" + c5 + ".jw" + c6),
					{
						width : ag(c5 + "Rail", c2).width
								+ (c2 ? 0 : (ag(c5 + "Cap" + c7).width
										+ ag(c5 + "RailCap" + c7).width
										+ ag(c5 + "RailCap" + c3).width + ag(c5
										+ "Cap" + c3).width)),
						height : c2 ? (ag(c5 + "Cap" + c7).height
								+ ag(c5 + "Rail").height
								+ ag(c5 + "RailCap" + c7).height
								+ ag(c5 + "RailCap" + c3).height + ag(c5
								+ "Cap" + c3).height) : ""
					});
			c4.className += " jw" + c6
		}
		var bc = {};
		function bv() {
			bp("left");
			bp("center");
			bp("right");
			cd(aK, bc.left);
			cd(aK, bc.center);
			cd(aK, bc.right);
			bi();
			s.style(bc.right, {
				right : ag("capRight").width
			})
		}
		function bi() {
			if (a2.hd) {
				M = new j.menu("hd", cI + "_hd", cH, H);
				if (!w) {
					a7(M, a2.hd, bA, ct)
				} else {
					cj(M, a2.hd, bA, "hd")
				}
				bk.hd = M
			}
			if (a2.cc) {
				an = new j.menu("cc", cI + "_cc", cH, bH);
				if (!w) {
					a7(an, a2.cc, cZ, bS)
				} else {
					cj(an, a2.cc, cZ, "cc")
				}
				bk.cc = an
			}
			if (a2.mute && a2.volume && a2.volume.vertical) {
				ba = new j.overlay(cI + "_volumeoverlay", cH);
				ba.setContents(a2.volume);
				bk.volume = ba
			}
		}
		function bS() {
			cU = setTimeout(an.hide, 500)
		}
		function ct() {
			U = setTimeout(function() {
				M.hide();
				bF = false
			}, 500)
		}
		function a7(c2, c4, c5, c6) {
			if (w) {
				return
			}
			var c3 = c2.element();
			cd(c4, c3);
			c4.addEventListener("mousemove", c5, false);
			if (c6) {
				c4.addEventListener("mouseout", c6, false)
			} else {
				c4.addEventListener("mouseout", c2.hide, false)
			}
			s.style(c3, {
				left : "75%",
				bottom : "45px"
			})
		}
		function cj(c3, c6, c7, c2) {
			if (!w) {
				return
			}
			var c5 = c3.element();
			cd(c6, c5);
			var c4 = new y.touch(c6);
			c4.addEventListener(y.touchEvents.TAP, function() {
				cS(c3, c7, c2)
			})
		}
		function cS(c3, c4, c2) {
			if (c2 === "cc") {
				if (b8.length === 2) {
					c4 = a6
				}
				if (bq) {
					cA();
					c3.hide()
				} else {
					bq = setTimeout(function() {
						c3.hide();
						bq = g
					}, 4000);
					c4()
				}
				bK.sendEvent(b.JWPLAYER_USER_ACTION)
			} else {
				if (c2 === "hd") {
					if (aZ.length === 2) {
						c4 = cW
					}
					if (aS) {
						cV();
						c3.hide()
					} else {
						aS = setTimeout(function() {
							c3.hide();
							aS = g
						}, 4000);
						c4()
					}
					bK.sendEvent(b.JWPLAYER_USER_ACTION)
				}
			}
		}
		function bp(c3) {
			var c2 = ce();
			c2.className = "jwgroup jw" + c3;
			bc[c3] = c2;
			if (aN[c3]) {
				aY(aN[c3], bc[c3], c3)
			}
		}
		function aY(c5, c2, c6) {
			if (c5 && c5.elements.length > 0) {
				for (var c4 = 0; c4 < c5.elements.length; c4++) {
					var c3 = aX(c5.elements[c4], c6);
					if (c3) {
						if (c5.elements[c4].name === "volume" && c3.vertical) {
							ba = new j.overlay(cI + "_volumeOverlay", cH);
							ba.setContents(c3)
						} else {
							cd(c2, c3)
						}
					}
				}
			}
		}
		function az() {
			return (A && y.isIE() && cF.jwGetFullscreen())
		}
		function aF() {
			clearTimeout(Q);
			Q = setTimeout(bK.redraw, 0)
		}
		bK.redraw = function(c4) {
			s.block(cI);
			cf();
			var c8 = A && y.isMSIE();
			var c3 = cM.active;
			s.style(a2.fullscreen, {
				display : (bw || c3 || cr || c8) ? "none" : ""
			});
			s.style(a2.volumeH, {
				display : "block",
				bottom : "-18px",
				left : "5px"
			});
			var c7 = Math.floor(bB.maxwidth);
			if (c7) {
				if (aK.parentNode && y.isIE()) {
					if (!bw
							&& aK.parentNode.clientWidth > c7
									+ (Math.floor(bB.margin) * 2)) {
						s.style(aK, {
							width : c7
						})
					} else {
						s.style(aK, {
							width : ""
						})
					}
				}
			}
			if (ba) {
				s.style(ba.element(), {
					display : !(bw || bf) ? "block" : "none"
				})
			}
			s.style(a2.hd, {
				display : !bw && !c3 && bM() ? "" : "none"
			});
			s.style(a2.cc, {
				display : !bw && cX() ? "" : "none"
			});
			bd();
			s.unblock(cI);
			if (bK.visible) {
				var c6 = ag("capLeft"), c5 = ag("capRight"), c2;
				if (az()) {
					c2 = {
						left : Math.round(c6.width),
						right : Math.round(c5.width)
					}
				} else {
					c2 = {
						left : Math.round(c6.width),
						right : Math.round(c5.width)
					}
				}
				s.style(bc.center, c2)
			}
			if (G == "small" && bu) {
				aK.style.bottom = "-36px"
			}
		};
		function aw() {
			if (!bl && cF.jwGetPlaylist().length > 1 && !aL()) {
				s.style(a2.next, a);
				s.style(a2.prev, a)
			} else {
				s.style(a2.next, v);
				s.style(a2.prev, v)
			}
		}
		function V(c4, c3) {
			if (!aB) {
				aB = y.bounds(aK)
			}
			var c2 = true;
			c3.constrainX(aB, c2);
			if (c4 == "hd") {
				c3.positionY(55)
			}
		}
		bK.audioMode = function(c2) {
			if (c2 !== g && c2 !== bw) {
				bw = !!c2;
				aF()
			}
			return bw
		};
		bK.instreamMode = function(c2) {
			if (c2 !== g && c2 !== bf) {
				bf = !!c2;
				s.style(a2.cast, bf ? v : a)
			}
			return bf
		};
		bK.adMode = function(c2) {
			if (C.isBoolean(c2) && c2 !== bl) {
				bl = c2;
				if (c2) {
					q(bX, a2.elapsed);
					q(bX, a2.duration)
				} else {
					o(bX, a2.elapsed);
					o(bX, a2.duration)
				}
				s.style([ a2.cast, a2.elapsed, a2.duration ], c2 ? v : a);
				aw()
			}
			return bl
		};
		bK.hideFullscreen = function(c2) {
			if (c2 !== g && c2 !== cr) {
				cr = !!c2;
				aF()
			}
			return cr
		};
		bK.element = function() {
			return aK
		};
		bK.margin = function() {
			return parseInt(bB.margin, 10)
		};
		bK.height = function() {
			return cm
		};
		function a9(c2) {
			if (a2.timeSliderBuffer) {
				c2 = Math.min(Math.max(0, c2), 1);
				s.style(a2.timeSliderBuffer, {
					width : (c2 * 100).toFixed(1) + "%",
					opacity : c2 > 0 ? 1 : 0
				})
			}
		}
		function cD(c5, c9) {
			if (!a2[c5]) {
				return
			}
			var c8 = 100 * Math.min(Math.max(0, c9), 1) + "%";
			if (c5 != "time") {
				c8 = 82 * Math.min(Math.max(0, c9), 1) + "%"
			}
			var c6 = a2[c5].vertical, c7 = c5 + (c5 === "time" ? "Slider" : ""), da = c8, c2 = a2[c7
					+ "Progress"], c3 = a2[c7 + "Thumb"], c4;
			if (c2) {
				c4 = {};
				if (c6) {
					c4.height = da;
					c4.bottom = 0
				} else {
					c4.width = da
				}
				if (c5 !== "volume") {
					c4.opacity = (c9 > 0 || ac) ? 1 : 0
				}
				s.style(c2, c4)
			}
			if (c3) {
				c4 = {};
				if (c6) {
					c4.top = 0
				} else {
					c4.left = da
				}
				s.style(c3, c4)
			}
		}
		function aM(c2) {
			cD("volume", c2);
			cD("volumeH", c2)
		}
		function Y(c2) {
			cD("time", c2)
		}
		function ag(c3) {
			var c2 = "controlbar", c5, c4 = c3;
			if (c3.indexOf("volume") === 0) {
				if (c3.indexOf("volumeH") === 0) {
					c4 = c3.replace("volumeH", "volume")
				} else {
					c2 = "tooltip"
				}
			}
			c5 = cH.getSkinElement(c2, c4);
			if (c5) {
				return c5
			} else {
				return {
					width : 0,
					height : 0,
					src : "",
					image : g,
					ready : false
				}
			}
		}
		function cd(c2, c3) {
			c2.appendChild(c3)
		}
		var bn, P, bU = true;
		bK.railBig = function() {
			bV.style.height = "20px";
			bV.style.bottom = "10px";
			W.style.visibility = "visible"
		};
		bK.railSmall = function() {
			bV.style.height = "2px";
			bV.style.bottom = "18px";
			W.style.visibility = "hidden"
		};
		var G = "small";
		var aq, b1, bu = true;
		var cG = false;
		bK.show = function(c3) {
			if (G == "big" && !c3) {
				return
			}
			if (!bu || G == "big") {
				return
			}
			if (cF.jwGetFullscreen()) {
				var c5 = (new Date()).getTime();
				var c2 = c5 - bL;
				if (cF.jwGetState() != f.PAUSED) {
					if (ah && c2 < 2000) {
						return
					}
					if (!ah && c2 < 1000) {
						return
					}
					ah = false
				}
			}
			bK.visible = true;
			var c4 = {
				display : "inline-block"
			};
			s.style(aK, c4);
			aB = y.bounds(aK);
			am();
			s.block(cI);
			s.style(aK, {
				opacity : 1
			});
			aD();
			bx(c3)
		};
		var bL = 0;
		var bQ = 0;
		function bx(c2) {
			if (bu == false) {
				return
			}
			clearInterval(aq);
			clearInterval(b1);
			var c3 = -36;
			aK.style.bottom = "-36px";
			bQ = (new Date()).getTime();
			cG = false;
			bu = false;
			aq = setInterval(function() {
				c3 += 2;
				aK.style.bottom = c3 + "px";
				if (c3 == 0) {
					clearInterval(aq);
					aK.style.bottom = "0px";
					G = "big";
					bu = true;
					b6();
					ad = setTimeout(function() {
						aK.style.bottom = "0px"
					}, 0)
				}
			}, 10)
		}
		bK.hide = function() {
			if (G == "small") {
				return
			}
			if (bf && w && cF.jwGetControls()) {
				return
			}
			var c2 = (new Date()).getTime();
			if ((c2 - bQ) < 2000) {
				return
			}
			c1()
		};
		function c1() {
			if (bu && G == "big") {
				clearInterval(aq);
				clearInterval(b1);
				b6();
				ad = setTimeout(function() {
					s.style(aK, {
						opacity : 1
					})
				}, B);
				cG = false;
				var c2 = 0;
				bK.railSmall();
				b1 = setInterval(function() {
					bu = false;
					c2 += -2;
					aK.style.bottom = c2 + "px";
					if (c2 == -36) {
						aK.style.bottom = "-36px";
						clearInterval(b1);
						bu = true;
						G = "small";
						if (cF.jwGetFullscreen()) {
							bL = (new Date()).getTime()
						}
						cG = true;
						b6();
						ad = setTimeout(function() {
							aK.style.bottom = "-36px"
						}, B)
					}
				}, 10)
			}
		}
		bK.showVolume = function() {
			if (bw || bf) {
				return
			}
			s.block(cI);
			ba.show();
			V("volume", ba);
			J("volume")
		};
		bK.hideVolume = function() {
			ba.hide()
		};
		bK.showTemp = function() {
			if (!this.visible) {
				aK.style.opacity = 0;
				aK.style.display = "inline-block"
			}
		};
		bK.hideTemp = function() {
			if (!this.visible) {
				aK.style.display = "none"
			}
		};
		function b6() {
			clearTimeout(ad);
			ad = -1
		}
		function cA() {
			clearTimeout(bq);
			bq = g
		}
		function cV() {
			clearTimeout(aS);
			aS = g
		}
		function aE(c2) {
			if (c2) {
				y.ajax(c2, bG, bT, true)
			} else {
				bP.length = 0
			}
		}
		function bG(c2) {
			var c3 = new p.parsers.srt().parse(c2.responseText, true);
			if (!C.isArray(c3)) {
				return bT("Invalid data")
			}
			bK.addCues(c3)
		}
		bK.addCues = function(c2) {
			y.foreach(c2, function(c3, c4) {
				if (c4.text) {
					bm(c4.begin, c4.text)
				}
			})
		};
		function bT(c2) {
			y.log("Cues failed to load: " + c2)
		}
		i.controlbar_jw = bK;
		al()
	};
	(function() {
		var F = "absolute", E = "relative", D = "opacity .25s, background .25s, visibility .25s", G = "span.jwcontrolbar";
		s(G, {
			position : F,
			margin : "auto",
			opacity : 0,
			display : "none"
		});
		s(G + " span", {
			height : "100%"
		});
		y.dragStyle(G + " span", "none");
		s(G + " .jwgroup", {
			display : "inline"
		});
		s(G + " span, " + G + " .jwgroup button," + G + " .jwleft", {
			position : E,
			"float" : "left"
		});
		s(G + " .jwleft", {
			left : 17,
			bottom : -4
		});
		s(G + " .jwright", {
			position : E,
			"float" : "right",
			left : -17,
			bottom : -4
		});
		s(G + " .jwcenter", {
			position : F,
			left : 0,
			right : 0,
			height : 12,
			top : 8
		});
		s(G + " button", {
			display : "inline-block",
			height : "100%",
			border : "none",
			cursor : "pointer"
		});
		s(G + " .jwcapRight," + G + " .jwtimeSliderCapRight," + G
				+ " .jwvolumeCapRight", {
			right : 0,
			position : F
		});
		s(G + " .jwcapBottom", {
			bottom : 0,
			position : F
		});
		s(G + " .jwtime", {
			position : F,
			height : "100%",
			width : "100%",
			left : 0
		});
		s(G + " .jwthumb", {
			position : F,
			height : "100%",
			cursor : "pointer"
		});
		s(G + " .jwvolumeHThumb", {
			bottom : "12px",
			width : "15px",
			height : "16px"
		});
		s(G + " .jwvolumeHThumb. jwthumb", {
			width : "15px"
		});
		s(G + " .jwmute", {
			left : "-7px"
		});
		s(G + " .jwspeed", {
			left : "5px"
		});
		s(G + " .jwtext.jwelapsed.jwhidden", {
			left : "-10px"
		});
		s(G + " .jwrail", {
			position : F,
			cursor : "pointer",
			bottom : "18px"
		});
		s(G + " .jwrailgroup", {
			position : F,
			width : "100%"
		});
		s(G + " .jwrailgroup span", {
			position : F
		});
		s(G + " .jwdivider+.jwdivider", {
			display : "none"
		});
		s(G + " .jwtext", {
			padding : "0 5px",
			"text-align" : "center"
		});
		s(G + " .jwcast", {
			display : "none"
		});
		s(G + " .jwcast.jwcancast", {
			display : "block"
		});
		s(G + " .jwalt", {
			display : "none",
			overflow : "hidden"
		});
		s(G + " .jwalt", {
			position : F,
			left : 0,
			right : 0,
			"text-align" : "left"
		}, true);
		s(G + " .jwoverlaytext", {
			padding : 3,
			"text-align" : "center"
		});
		s(G + " .jwvertical *", {
			display : "block"
		});
		s(G + " .jwvertical .jwvolumeProgress", {
			height : "auto"
		}, true);
		s(G + " .jwprogressOverflow", {
			position : F,
			overflow : "hidden"
		});
		z(G, D);
		z(G + " button", D);
		z(G + " .jwtoggling", "none")
	})()
})(window, document);
(function(d) {
	var c = d.html5, a = d.utils, e = d.events, b = e.state, f = d.playlist, g;
	c.controller = function(o, k) {
		var u = false, q = -1, E = false, O, S = false, i, C = [], p = a
				.extend(this, new e.eventdispatcher(o.id, o.config.debug));
		function T() {
			o.addEventListener(e.JWPLAYER_MEDIA_BUFFER_FULL, t);
			o.addEventListener(e.JWPLAYER_MEDIA_COMPLETE, function() {
				setTimeout(H, 25)
			});
			o.addEventListener(e.JWPLAYER_MEDIA_ERROR, function(Y) {
				var X = a.extend({}, Y);
				X.type = e.JWPLAYER_ERROR;
				p.sendEvent(X.type, X)
			})
		}
		function s() {
			return o.getVideo()
		}
		function w(X) {
			if (!u) {
				k.completeSetup();
				p.sendEvent(X.type, X);
				if (d.utils.exists(d.playerReady)) {
					d.playerReady(X)
				}
				o.addGlobalListener(r);
				k.addGlobalListener(r);
				p.sendEvent(d.events.JWPLAYER_PLAYLIST_LOADED, {
					playlist : d(o.id).getPlaylist()
				});
				p.sendEvent(d.events.JWPLAYER_PLAYLIST_ITEM, {
					index : o.item
				});
				Q();
				if (o.autostart && !a.isMobile()) {
					I()
				} else {
					g = document.getElementById("video-box_display");
					j();
					g.addEventListener("click", v)
				}
				u = true;
				while (C.length > 0) {
					var Y = C.shift();
					G(Y.method, Y.arguments)
				}
			}
		}
		function v() {
			I();
			g.removeEventListener("click", v)
		}
		function r(X) {
			p.sendEvent(X.type, X)
		}
		function t() {
			s().play()
		}
		function Q(X) {
			B(true);
			switch (a.typeOf(X)) {
			case "string":
				V(X);
				break;
			case "object":
			case "array":
				o.setPlaylist(new d.playlist(X));
				break;
			case "number":
				o.setItem(X);
				break
			}
		}
		function V(Y) {
			var X = new f.loader();
			X.addEventListener(e.JWPLAYER_PLAYLIST_LOADED, function(Z) {
				Q(Z.playlist)
			});
			X.addEventListener(e.JWPLAYER_ERROR, function(Z) {
				Q([]);
				Z.message = "Could not load playlist: " + Z.message;
				r(Z)
			});
			X.load(Y)
		}
		function I(Y) {
			if (!a.exists(Y)) {
				Y = true
			} else {
				if (!Y) {
					return j()
				}
			}
			try {
				if (q >= 0) {
					Q(q);
					q = -1
				}
				if (!E) {
					E = true;
					p.sendEvent(e.JWPLAYER_MEDIA_BEFOREPLAY);
					E = false;
					if (i) {
						i = false;
						O = null;
						return
					}
				}
				if (h()) {
					if (o.playlist.length === 0) {
						return false
					}
					s().load(o.playlist[o.item])
				} else {
					if (o.state === b.PAUSED) {
						s().play()
					}
				}
				return true
			} catch (X) {
				p.sendEvent(e.JWPLAYER_ERROR, X);
				O = null
			}
			return false
		}
		function B(X) {
			O = null;
			try {
				s().stop();
				if (!X) {
					S = true
				}
				if (E) {
					i = true
				}
				return true
			} catch (Y) {
				p.sendEvent(e.JWPLAYER_ERROR, Y)
			}
			return false
		}
		function j(Y) {
			O = null;
			if (!a.exists(Y)) {
				Y = true
			} else {
				if (!Y) {
					return I()
				}
			}
			switch (o.state) {
			case b.PLAYING:
			case b.BUFFERING:
				try {
					s().pause()
				} catch (X) {
					p.sendEvent(e.JWPLAYER_ERROR, X);
					return false
				}
				break;
			default:
				if (E) {
					i = true
				}
			}
			return true
		}
		function h() {
			return (o.state === b.IDLE)
		}
		function D(X) {
			if (o.state !== b.PLAYING) {
				I(true)
			}
			s().seek(X)
		}
		function z(X) {
			k.fullscreen(X)
		}
		function J(X) {
			a.css.block(o.id + "_next");
			Q(X);
			I();
			a.css.unblock(o.id + "_next")
		}
		function K() {
			J(o.item - 1)
		}
		function l() {
			J(o.item + 1)
		}
		function H() {
			if (!h()) {
				return
			} else {
				if (S) {
					S = false;
					return
				}
			}
			O = H;
			if (o.repeat) {
				l()
			} else {
				if (o.item === o.playlist.length - 1) {
					q = 0;
					B(true);
					setTimeout(function() {
						p.sendEvent(e.JWPLAYER_PLAYLIST_COMPLETE)
					}, 0)
				} else {
					l()
				}
			}
		}
		function A(X) {
			s().setCurrentQuality(X)
		}
		function U() {
			if (s()) {
				return s().getCurrentQuality()
			}
			return -1
		}
		function R() {
			if (s()) {
				return s().getQualityLevels()
			}
			return null
		}
		function x(X) {
			s().setCurrentAudioTrack(X)
		}
		function L() {
			if (s()) {
				return s().getCurrentAudioTrack()
			}
			return -1
		}
		function M() {
			if (s()) {
				return s().getAudioTracks()
			}
			return null
		}
		function W(X) {
			k.setCurrentCaptions(X)
		}
		function N() {
			return k.getCurrentCaptions()
		}
		function F() {
			return k.getCaptionsList()
		}
		function y() {
			try {
				return o.getVideo().detachMedia()
			} catch (X) {
				a.log("Error calling detachMedia", X)
			}
			return null
		}
		function n(Y) {
			try {
				o.getVideo().attachMedia(Y)
			} catch (X) {
				a.log("Error calling detachMedia", X);
				return
			}
			if (typeof O === "function") {
				O()
			}
		}
		function P(X) {
			return function() {
				var Y = Array.prototype.slice.call(arguments, 0);
				if (u) {
					G(X, Y)
				} else {
					C.push({
						method : X,
						arguments : Y
					})
				}
			}
		}
		function G(Y, X) {
			Y.apply(this, X)
		}
		this.play = P(I);
		this.pause = P(j);
		this.seek = P(D);
		this.stop = function() {
			if (h()) {
				S = true
			}
			P(B)()
		};
		this.load = P(Q);
		this.next = P(l);
		this.prev = P(K);
		this.item = P(J);
		this.setVolume = P(o.setVolume);
		this.setMute = P(o.setMute);
		this.setFullscreen = P(z);
		this.detachMedia = y;
		this.attachMedia = n;
		this.setCurrentQuality = P(A);
		this.getCurrentQuality = U;
		this.getQualityLevels = R;
		this.setCurrentAudioTrack = x;
		this.getCurrentAudioTrack = L;
		this.getAudioTracks = M;
		this.setCurrentCaptions = P(W);
		this.getCurrentCaptions = N;
		this.getCaptionsList = F;
		this.checkBeforePlay = function() {
			return E
		};
		this.playerReady = w;
		T()
	}
})(jwplayer);
(function(b) {
	var c = '<?xml version="1.0" ?><skin author="JW Player" name="Six" target="6.7" version="3.0"><components><component name="controlbar"><settings><setting name="margin" value="0"/><setting name="maxwidth" value="2800"/><setting name="fontsize" value="12"/><setting name="fontweight" value="normal"/><setting name="fontcase" value="normal"/><setting name="fontcolor" value="0x787D82"/></settings><elements><element name="background" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAAoCAIAAACw1AcgAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDIxIDc5LjE1NTc3MiwgMjAxNC8wMS8xMy0xOTo0NDowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKFdpbmRvd3MpIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOjdBMThGNjM3MDM0RDExRTU5NEJGRjAwOUI4Q0FCN0FCIiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOjdBMThGNjM4MDM0RDExRTU5NEJGRjAwOUI4Q0FCN0FCIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6N0ExOEY2MzUwMzREMTFFNTk0QkZGMDA5QjhDQUI3QUIiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6N0ExOEY2MzYwMzREMTFFNTk0QkZGMDA5QjhDQUI3QUIiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz58nwlfAAAADklEQVR42mJgGNwAIMAAAKAAAed8h7IAAAAASUVORK5CYII="/><!--element name="capLeft" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAgAAAAeCAYAAAARgF8NAAAAr0lEQVR4AWNhAAJRUXEFIFUOxNZAzMOABFiAkkpAeh0fH5+IgoKCKBsQoCgA4lJeXl5ReXl5qb9//zJ8+/aNAV2Btbi4uOifP39gYhgKeFiBAEjjUAAFlCn4/5+gCf9pbwVhNwxhKxAm/KdDZA16E778/v37DwsLKwsuBUdfvXopISUlLYpLQc+vX78snz17yigqKibAAgQoCuTlFe4+fPggCKio9OnTJzZAMW5kBQAEFD9DdqDrQQAAAABJRU5ErkJggg=="/><element name="capRight" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAgAAAAeCAYAAAARgF8NAAAArklEQVR4Ad2TMQrCQBBF/y5rYykEa++QxibRK3gr0dt4BPUSLiTbKMYUSlgt3IFxyogJsRHFB6/7/A+7jIqiYYZnvLgV56IzcRyPUOMuOOcGVVWNAcxUmk4ZNZRS0Fojz/O9936lkmTCaICIgrV2Z9CCMaYHoK/RQWfAMHcEAP7QxPsNAP/BBDN/+7N+uoEoEIBba0NRHM8A1i8vSUJZni4hhAOAZdPxXsWNuBCzB0E+V9jBVxF8AAAAAElFTkSuQmCC"/--><element name="playButton" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMDY3IDc5LjE1Nzc0NywgMjAxNS8wMy8zMC0yMzo0MDo0MiAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6RjhFQzgyMDY5MThCMTFFNTg2QTRDNzU4RUUyMzQxMEIiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6RjhFQzgyMDU5MThCMTFFNTg2QTRDNzU4RUUyMzQxMEIiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKFdpbmRvd3MpIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6MDk3QzYxRTE5MThCMTFFNUI0QTI5NjQxNEM0MzBEMDMiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6MDk3QzYxRTI5MThCMTFFNUI0QTI5NjQxNEM0MzBEMDMiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz5rcFh2AAAAh0lEQVR42pyS4QmAIBCFVW6F3KwaohYr2qamsF9NYHegIKHdeQ8+wqD3vigbYzSUZ7B02ZAVuY0w7nOekAsZtQUUj+wJrynIIYuTs3HMAGvjhK/atJEWNG16Cqo2mgKKTRhQPHwgCxJ6DejvnJN6yDdBs9rzFaqrZUCzyhmwq38GotUyrwADAFOyICvEROhlAAAAAElFTkSuQmCC"/><element name="playButtonOver" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMDY3IDc5LjE1Nzc0NywgMjAxNS8wMy8zMC0yMzo0MDo0MiAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6RkY4QkY2RTg5MThCMTFFNTkzNTM4M0I5NzM1OTk3RDUiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6RkY4QkY2RTc5MThCMTFFNTkzNTM4M0I5NzM1OTk3RDUiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKFdpbmRvd3MpIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6MDk3QzYxRTE5MThCMTFFNUI0QTI5NjQxNEM0MzBEMDMiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6MDk3QzYxRTI5MThCMTFFNUI0QTI5NjQxNEM0MzBEMDMiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz6eWvwUAAAAhklEQVR42pyS4QmAIBCFVZzBzaoJ/FWLFW2TU9gQdhcKEtqd9+ALDHrvi9IpJfXGe7zuwAbcihnzOc9AACZpAcYBR8ZJCkrQ4qJsDDFA2hjmq3ZtuAVdm5GCpo2kAKMzygoePoEViKMG+HcuWT2Wm1ayOvIVmqt1rGSVMiBX/wxYq3UeAQYAXOAf7uHeJ5UAAAAASUVORK5CYII="/><element name="pauseButton" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMDY3IDc5LjE1Nzc0NywgMjAxNS8wMy8zMC0yMzo0MDo0MiAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6NTJDRUQ4MTg5MThDMTFFNUE2NUZDNjQ4OEMxQzQ4MTQiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6NTJDRUQ4MTc5MThDMTFFNUE2NUZDNjQ4OEMxQzQ4MTQiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKFdpbmRvd3MpIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6NDlEMjA5QTgyOTA2MTFFNTk2NDg4OTk3Rjc0NEFDRjkiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6NDlEMjA5QTkyOTA2MTFFNTk2NDg4OTk3Rjc0NEFDRjkiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz7zq0uVAAAAOUlEQVR42mL8//8/w0dRRgZyAP/r/wwsSPz/aPLopmKVZ2KgEIwaMGrAqAEQwIIn9zEQyJ1gABBgAIKJCCO+oOX0AAAAAElFTkSuQmCC"/><element name="pauseButtonOver" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMDY3IDc5LjE1Nzc0NywgMjAxNS8wMy8zMC0yMzo0MDo0MiAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6NThENUE5RTI5MThDMTFFNTg1OERDQzA1RDdGRTVFOTYiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6NThENUE5RTE5MThDMTFFNTg1OERDQzA1RDdGRTVFOTYiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKFdpbmRvd3MpIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6NDlEMjA5QTgyOTA2MTFFNTk2NDg4OTk3Rjc0NEFDRjkiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6NDlEMjA5QTkyOTA2MTFFNTk2NDg4OTk3Rjc0NEFDRjkiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz5p9dLUAAAAOUlEQVR42mL8//8/A0NqKgNZYPZsBhYk7n80aUY0PlZ5JgYKwagBowaMGgABLHhyHwOB3AkGAAEGAKH1ByPcurPTAAAAAElFTkSuQmCC"/><element name="prevButton" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABIAAAAeCAQAAACLBYanAAAAmElEQVR4AWMYMDAKeBgkgBgGmBn4GUQZONEVqfzfz6ACV6Bekv5gMYMcuiKDR/sZDGAKrqz5sf/lfgZdDEW39jPYQxR82/94/y0gZDDAUHR+f3rpjZWf99/efx4CsSk6sj+pbMvKI/vhEJuiXWDrQjNmr921HwyxKVoPd3hAxsS16/evx+JwleUoQeCbMRkRBIQDk/5gFAAAvD5I9xunLg8AAAAASUVORK5CYII="/><element name="prevButtonOver" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABIAAAAeCAQAAACLBYanAAAAmUlEQVR4AWMYMDAKBBgUgBgGWBhEGGQYeNAVGfz/z2AAV2BS0vXgJoMGuiKHR/8ZHGAKrjz78f/lfwYbDEW3/jOEQBR8+//4/y0gZHDAUHT+f/qcGw8//7/9/zwEYlN05H/S3C2PjvyHQ2yKdoGtC+2e/XzXfzDEpmg93OEB3ROfr/+/HovDDZajBIFv9+RbDBpEByb9wSgAAHeuVc8xgA8jAAAAAElFTkSuQmCC"/><element name="nextButton" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABEAAAAeCAQAAABgMj2kAAAAlUlEQVR4AWOgAxgFnAyiDPwMzHA+D4MEEKMAuQeLS9IZ1OHKVP7vZ1BBVaL7cv+P/VfWwJUZPNrPYICqxODW/lv7H+//BlNmfwtTyfn9EHh7/+f9N1aml57HVHJkPwJuWZlUdgRTya79EDh7bWgGyKJdGEp01+9fv3/i2oAMmHPXYyiRm7zYNwPZ08vBniYcdDQHowAA/MZI93f1cSkAAAAASUVORK5CYII="/><element name="nextButtonOver" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABEAAAAeCAQAAABgMj2kAAAAlUlEQVR4AWOgAxgFPAwyDCIMLHC+AIMCEKMAjQc3S7oYTODKDP7/ZzBAVWLz8v+P/1eewZU5PPrP4ICqxOHW/1v/H///BlMWcgtTyfn/EHj7/+f/Nx6mzzmPqeTIfwTc8ihp7hFMJbv+Q+Ds56HdIIt2YSixWf9//f+JzwO6Yc5dj6FEY/It325kTy8He5pw0NEcjAIAWP9Vz4mR7dgAAAAASUVORK5CYII="/><element name="elapsedBackground" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAYAAAAeCAYAAAAPSW++AAAAD0lEQVQoU2NgGAWjYKQAAALuAAGL6/H9AAAAAElFTkSuQmCC"/><element name="durationBackground" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAYAAAAeCAYAAAAPSW++AAAAD0lEQVQoU2NgGAWjYKQAAALuAAGL6/H9AAAAAElFTkSuQmCC"/><!--element name="timeSliderCapLeft" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAMAAAAeCAYAAADpYKT6AAAAFElEQVR42mP4//8/AwwzjHIGhgMAcFgNAkNCQTAAAAAASUVORK5CYII="/><element name="timeSliderCapRight" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAMAAAAeCAYAAADpYKT6AAAAFElEQVR42mP4//8/AwwzjHIGhgMAcFgNAkNCQTAAAAAASUVORK5CYII="/--><element name="timeSliderRail" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAAICAIAAAC3eAIWAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDIxIDc5LjE1NTc3MiwgMjAxNC8wMS8xMy0xOTo0NDowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKFdpbmRvd3MpIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOjlBMUQyRkE1MjZBNjExRTU4NDg1QjJCNzFBQkE2NUY3IiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOjlBMUQyRkE2MjZBNjExRTU4NDg1QjJCNzFBQkE2NUY3Ij4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6OUExRDJGQTMyNkE2MTFFNTg0ODVCMkI3MUFCQTY1RjciIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6OUExRDJGQTQyNkE2MTFFNTg0ODVCMkI3MUFCQTY1RjciLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz450VHmAAAAFElEQVR42mIws3FgYmBgwIUBAgwAFdIAwU/edcMAAAAASUVORK5CYII="/><!--element name="timeSliderRailCapLeft" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAUAAAAeCAYAAADkftS9AAAAnUlEQVR42t3NSwrCMBSF4TsQBHHaaklJKRTalKZJ+lAXoTPBDTlyUYprKo6PN4F2D3rgm/yQG/rfRdHuwp5smsNdCImiKKFUAx/OaSpR1xpNYwKK4/2rLBXa1s1CnIxxsLZbhGhtD+eGBSWJePt7fX9YUFXVVylzdN2IYTgGBGCVZfmDQWuDcTyB/ACsOdz8Kf7jQ/P8C7ZhW/rlfQGDz0pa/ncctQAAAABJRU5ErkJggg=="/><element name="timeSliderRailCapRight" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAUAAAAeCAYAAADkftS9AAAAn0lEQVR42t3MTwqCQBTH8bcIgmirJYoiCOowzh8ds0PULjpRqw5VdCZr/WueMJfwC5/NezOP1lcUHWbv5V0o1LYSVVUjTXP4xYM4KTWYEB2ybFlcSSmLoK4F4vj4JmN6BFpbHs5krUNgzMDDLw3DCQHfTZL0Q85NYH0/Is9LNI240Tie0XUaRVGyJ4AN+Rs//qKUuQPYEgdg7+2WF2voDzqVSl5A2koAAAAAAElFTkSuQmCC"/--><element name="timeSliderBuffer" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAAICAIAAAC3eAIWAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDIxIDc5LjE1NTc3MiwgMjAxNC8wMS8xMy0xOTo0NDowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKFdpbmRvd3MpIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOkYyNUY3OUYyNENBQjExRTU5OEUzRDEzQjJCMzNBMjdEIiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOkYyNUY3OUYzNENBQjExRTU5OEUzRDEzQjJCMzNBMjdEIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6RjI1Rjc5RjA0Q0FCMTFFNTk4RTNEMTNCMkIzM0EyN0QiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6RjI1Rjc5RjE0Q0FCMTFFNTk4RTNEMTNCMkIzM0EyN0QiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz5bZ7+CAAAAFElEQVR42mLwDY1kYmBgwIUBAgwAHl4BChtyxhcAAAAASUVORK5CYII="/><!--element name="timeSliderBufferCapLeft" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAUAAAAeCAYAAADkftS9AAAAY0lEQVR42uXJyxGAIAxFUfrgI5CgzajdqlWxQffxaeiCzJyZ5MYMNtb6zTl/OhfuP2BZQ4h1mpLEmOWPCMd3pESSM2vE0YiKdBqJuDEXUT0yzydIp7GUZYMKAhr7Y4cLHjPGvMB5JcRMsOVwAAAAAElFTkSuQmCC"/><element name="timeSliderBufferCapRight" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAUAAAAeCAYAAADkftS9AAAAYElEQVQoz+WLyxGAIAwF6YM/CdqMlCtdcRHvMSIw9sCb2ctuIsQaU8pUpfQppT6mdC6QtZ6McYUPUpMhIHkP9EYOuUmASAOOV5OIkQYAWLvc6Mf3HuNOncKkIW8mT7HOHpUUJcPzmTX0AAAAAElFTkSuQmCC"/--><element name="timeSliderProgress" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAAICAIAAAC3eAIWAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDIxIDc5LjE1NTc3MiwgMjAxNC8wMS8xMy0xOTo0NDowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKFdpbmRvd3MpIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOkE5RDZBRjU3MjZBNjExRTVBRDU3RUMwODIxN0Y0RTg1IiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOkE5RDZBRjU4MjZBNjExRTVBRDU3RUMwODIxN0Y0RTg1Ij4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6QTlENkFGNTUyNkE2MTFFNUFENTdFQzA4MjE3RjRFODUiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6QTlENkFGNTYyNkE2MTFFNUFENTdFQzA4MjE3RjRFODUiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz57r7SeAAAAE0lEQVR42mL4IMLAxMCAEwMEGAAgaAETXYq34wAAAABJRU5ErkJggg=="/><!--element name="timeSliderProgressCapLeft" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAUAAAAeCAQAAABOdxw2AAAARUlEQVQYV2NkgANG+jP/+zJkMtgCmf99vi38KPQTJPpq6xsvqIKznxh4ocwjCOaebQyeUOZmX4YFDEJQw9b4QQ2DAfoyAVkTEmC7RwxJAAAAAElFTkSuQmCC"/><element name="timeSliderProgressCapRight" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAUAAAAeCAQAAABOdxw2AAAASklEQVQYV8XLIRKAMAxE0R4QbhrXoQqJxWJxCGZqaKs/m1yi+80TSUqzRmNjCd48jMoqXnhvEU+iTzyImrgT+UFG1exv1q2YY95+oTIxx/xENX8AAAAASUVORK5CYII="/--><element name="timeSliderThumb" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDIxIDc5LjE1NTc3MiwgMjAxNC8wMS8xMy0xOTo0NDowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKFdpbmRvd3MpIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOjRBRUM0NDdEMjZBNjExRTU5MkMxOUE0NTA5NkZDQkEwIiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOjRBRUM0NDdFMjZBNjExRTU5MkMxOUE0NTA5NkZDQkEwIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6NEFFQzQ0N0IyNkE2MTFFNTkyQzE5QTQ1MDk2RkNCQTAiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6NEFFQzQ0N0MyNkE2MTFFNTkyQzE5QTQ1MDk2RkNCQTAiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz7VcQonAAAA2klEQVR42mJ8+/ETAxrQAuIUIPYEYkWo2D0g3gHEc4D4GrJiRiQD2IC4D4gzgZiJATv4B8SzgbgQiL8jGwDSvBWIXRiIA/ugLvwFs6mfBM0g4AR1LdgF2kD6IhAzM5AG/gKxPhM0wEjVzADVkwIywIOBfOABMkCJAgOUmBgoA+BYuEeBAQ+ZoCmMXLAHZMBcaJQwkBGNc0AGXAHimWQYANJzBRaIoLS9hwTN+6B64JnmFxB7A/FUaIZhwJOZQDb7QPWg5EYYACXtVGgCU4IqfAB14Ryol+EAIMAASt4151wVYOAAAAAASUVORK5CYII="/><element name="timeSliderCue" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAeCAYAAAAl+Z4RAAAAcUlEQVQ4y2NgGAWjYBTgBaKi4llAfASKs0jWbGNj96S1tf03CIPYJBkCsrW6uu53bm7+fxAGsUFiJBmQlpbxOzMz5z8Ig9hAsaMkecHIyORJUlLq78TElN8gNlAsm9RwyAbZCsSHgDhzNFmNglGAHwAAo/gvURVBmFAAAAAASUVORK5CYII="/><element name="distanceButton" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAQAAAAYCAYAAADd5VyeAAAADklEQVQYlWNgGAWDEQAAAZgAAYLRFi4AAAAASUVORK5CYII="/><element name="hdButtonOff" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB0AAAAeCAYAAADQBxWhAAABf0lEQVR42u2VvUoDQRSFA0awMIVCsv+z/1oE8yOE9MYmtb2P4AspSOyECFZqtU9gbZvK6CNoNZ6zMMuSQpxdEAJbHC737pz59mbmblpSyn9XA22gDXRLod2uMYfWkKwh+uc60LVtO9J1RWXBn4N1oNL3QxkEEcwuzYybOWMh07QJ4xqK/ryuBQ3DWEZRoowdx3FfhAgkI3NVp7IsO5xMpnPDsFae59NHvzaURgWlWpblPEOSkbmqQzfQK2DT8fj0HB0rrz40jlOqgA4Go1m/f3LJWIYC8uQ4nkSX94vF3S5qX8qrDU2SlCqgOMMrAK4Zy1B27nlCIj4i34G+lbcC9ChXuSNeFEbmpZe5RZdv+BU4ZjM8V159aJoe5yp3JIS/eaZcv7dcPhzghc6Qr3DZlLc6FOelRoTn9OvI4DKxw2rQXs/84KzRyLPhTSSQGzIyV2OBdYzIYz4rgKxjn88/Q4fD0QUNNT6BBL5zH50Pfhvahzo1RH+7+WtroA10O6E/bVCWtAEB8p4AAAAASUVORK5CYII="/><element name="hdButton" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAQCAYAAAABOs/SAAAAJElEQVQ4jWNkYPjPyDAAgGkgLB21eNTiUYtHLR61eNTiwWkxAA4HASBlN8IuAAAAAElFTkSuQmCC"/><!--element name="hdButton" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAQCAYAAAABOs/SAAAAJElEQVQ4jWNMm/n/P8MAAKaBsHTU4lGLRy0etXjU4lGLB6fFABKoAx14z5I4AAAAAElFTkSuQmCC"/--><element name="ccButtonOff" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB0AAAAeCAYAAADQBxWhAAABzUlEQVR42u1Uu0oDQRQVTCMopMjmtZvdJPswKCQbC6tYCEqMBDUGrf2NCDF+gmXEyiZWiTb+gMTGxtrGwmh8IOKjUoLjueNGfCBk10rYC4eZOey5Z+7M3O1zww033Og5BCGQA9oAcw6uz9kxbYfDIpMk2TGg58Z2TJmixFg0GueIRBQWDIZ5BX5/kIli5AcfCIS6PIH0nLdlGoupLB7XmCxHyegymTSXa7UdoVBYHBVFqQEDMjozzfRCvd7w5fNzKfD74ElHevumEHKEQiJD4nmYz4JvwWirWt30YiO36fTYNKotgj8Hv1GprPvAP1obtm+qqjqBhC/l8toAkh18uqs7rK8ZY/0Yj8AT90o80LG09k01TQe48Bnw4O6asqzw5DjGXVR2Qt9iPLb4Dh07NnGvqhq0jkwNQvehTCYSI0tIeIWqtq1jfAA/bhiJFcxvcPzVUmlVwPwJVZLWvqmuD3MgGYlbGHPN5qE3m52JYU0PifhTGEwRn8lMaFjvYVNdrXNT7BjGX1tGkvgL/dYyxMv0vTNTahH02ocY1cBEpTbgeL8z41eeNKSn6+jZNJUyiyT4y28Q+gvK07MpWsEDDAJDzsH1nj433HDjX8YbqHFYmhICTLsAAAAASUVORK5CYII="/><element name="ccButton" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB0AAAAeCAQAAAB6Dt0qAAABWElEQVR4AWMY5mAUsDJIMBgy2DE44IR2QHkJoDoMINHQ/eTbl//44JNvDd1AzRjA8N63p/+f4IVP/9/7BrQZA9g9/H+fIHz4H+hsDOBw6z8EnvqZsJ6vznDCkke3/h/9Hr2ap9Z08oqnMFkGByxaL/+HwMiVafNufFl+hWvmiR+BC/IX3/yy4Bz/nJN/wbLYtZ75D4In/3GV7n56/v+1/zd/H/rGkHPgJYh94/fp/2B57FqP/AfBg/84SlY/O/L/8P+JLze/Z8je8PrI/0P/Jrza+Rcsj13r3v8guO9/+LKEhZu+9lzmn7zrl++c9BWbv7WfE5iy/S9YHrvWbf8hcP+P0FVsVSo9y57s+L/vm/9ytiqtvhVANlgWq1a79f8hcDPQR9eBAbIHyN7y/yyQfQnEhkCskWM4/9uq/4TgfKxJQiK6e/a3pf/xwZlfo4AJkZLkP6zBKAAAGMt/2TouFxQAAAAASUVORK5CYII="/><element name="muteButton" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMDY3IDc5LjE1Nzc0NywgMjAxNS8wMy8zMC0yMzo0MDo0MiAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6MzMyMkMwOEI5MTkwMTFFNUIwMkNCQzI3REI3MTcyMDEiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6MzMyMkMwOEE5MTkwMTFFNUIwMkNCQzI3REI3MTcyMDEiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKFdpbmRvd3MpIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6RDM2NDZCRjYyOTA2MTFFNThGQThBQjlFNDZBN0Q0MTQiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6RDM2NDZCRjcyOTA2MTFFNThGQThBQjlFNDZBN0Q0MTQiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz71OHQdAAABHElEQVR42qTSMUgCcRTHcRNxCEQHR5sicGgIRJDQaBI0aK+9HAyFAnFxcWmIEgqHahVqrKESx1CQHAUJdJMmJyGiBq3vwQseF3l3+IcP97//vf/v7t7dQrFUds0z3BbXA3LM4RZrTgL8aMh8hG10cGgnwIdHROX8Bit4xikyswIW8YB109obUmiigiXjgkcK9lXxLhKm0ANM5O576CGL4m/ApY1mH+Mer2gjbQS4HX6xlMy7WLbzGc3j2+l/oIfRg7rMV9HXTcyowh1smjZPUcAAYcRwogOuVHENT9hQa+f4hBfX+EL1v1f4wBZaas3YHJLgOPIYzurBu4S8qNfqy1MdyVNYNnGMpMyDuEMEZ7rIY9H5sRwvxJ/xI8AAbOY0dQ4A1dYAAAAASUVORK5CYII="/><element name="muteButtonOver" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMDY3IDc5LjE1Nzc0NywgMjAxNS8wMy8zMC0yMzo0MDo0MiAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6Mjg0ODhCMTQ5MTkwMTFFNUJCMjRDRjE3OTkwQjM5RTMiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6Mjg0ODhCMTM5MTkwMTFFNUJCMjRDRjE3OTkwQjM5RTMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKFdpbmRvd3MpIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6RDM2NDZCRjYyOTA2MTFFNThGQThBQjlFNDZBN0Q0MTQiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6RDM2NDZCRjcyOTA2MTFFNThGQThBQjlFNDZBN0Q0MTQiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz4W5amnAAABDklEQVR42mL8IMJAEWAiIC8ApfOAeAUQG5BiAD8Q74KyXwOxHxCfBuIiYgzgBeJtQGwK5S8HYlUgPgTEvUCcDlPIiCUMuIB4BxDbwtRAxb4BMRsQ7wViYyBWB+LHLFBFaUgGRCFphoEcIP4LtT0ViK8BcTYQV8Bc8B9PWIBcUAHErVBb7wDxMSDmAWI9JhJjzBPKvgzEysREIzr4T2o6QAZ/oYELAjpAfBvEgAViOpLCSCB2QNP8D4jLoP7XAGILIO7GF43bgdgOKRA5gPgHtmjE5gVQfHsD8VEkMZBmGajBNkCcD9KMLwy+QA05heSt21BXFQPxbGIC8SMQu0HZII9ugDq9DyWRUJqdAQIMAOWKNC8Lm2J9AAAAAElFTkSuQmCC"/><element name="unmuteButtonOver" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMDY3IDc5LjE1Nzc0NywgMjAxNS8wMy8zMC0yMzo0MDo0MiAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6NUM0ODQ1OTA5MTkwMTFFNUEyMkJFMjIyNjYxMjA5NDkiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6NUM0ODQ1OEY5MTkwMTFFNUEyMkJFMjIyNjYxMjA5NDkiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKFdpbmRvd3MpIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6RDM2NDZCRjYyOTA2MTFFNThGQThBQjlFNDZBN0Q0MTQiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6RDM2NDZCRjcyOTA2MTFFNThGQThBQjlFNDZBN0Q0MTQiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz7hwgDYAAABLElEQVR42qTTsUsCYRzG8c7EwKGpphbHqJZqbw7CKTOIoMYmhyYDU9L+hIYS14awRYSGhoKGCImgNYLWqIZaIoKy78lj/Hh5LwNf+PAe3vs+nM+9F7yODPQ1Ypp3UPDcT2heQQWjUQFfWlBwNh/relj37rHoCyij5IQsI63rfcziAUfIdgPiJqysuaJ5FxNm3Q3mcIYqLvA4mE92FixhEi8Ywxq+UVTAJqZwiRZy+MB5oLfQjii5qCdaRw3j6uEKQ5iO93hL9m+Fm+exh1usuh30CskjZcqP/Teg5Cl4Bnc2IGs2hAUueJ7Ahpyq5N+AutnQ0AFKOyFtE7KFpD1Idnwig6bz+wmeFbKN96gAX8gGrrX+0IT8WWI3JBxvOhMHeFKBnQ8t6Pdz/hFgAGp/QW4RF7sbAAAAAElFTkSuQmCC"/><element name="unmuteButton" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMDY3IDc5LjE1Nzc0NywgMjAxNS8wMy8zMC0yMzo0MDo0MiAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6NDVBNTFBODc5MTkwMTFFNUE1NUJFOTdFRkE3QjYzQzciIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6NDVBNTFBODY5MTkwMTFFNUE1NUJFOTdFRkE3QjYzQzciIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKFdpbmRvd3MpIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6RDM2NDZCRjYyOTA2MTFFNThGQThBQjlFNDZBN0Q0MTQiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6RDM2NDZCRjcyOTA2MTFFNThGQThBQjlFNDZBN0Q0MTQiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz4gP6oRAAABJklEQVR42qSSvUsDQRBHkxBSWKRLbxkstbcOxFSaCCLYWAg2lhFyBhP/hBQqtiJ+NCJYWFhYWIiIVgEJ2Eq0MYVFQM07+C0My54GHHjMsrvzbm920/WolfpPZJS3oRFYzykvQRsKSYIvbWh4xWca57XWg/mQIP6PpidZhIrGuzADL3AMNSfIGplrRlt5B6bMvgeYhWvYhxt4dYKqchduJUnDpuY3YKDCVcnWIXKCk0AD7Yne4UBff4Q7mLOCpLCSIpSgA0+w7PfgL0kdJk3zM+MKmoEGT8OzFdRMwQqUAyewkiv4toJTU3CuB1TxJD9GEt/OhH1INoawABfe/CW8SRLBZ5IgJFmDe+0/NJJfm+gkcXzAFuxBXw3MjXMLQ+WjhKtNjQQYAJMVQimyaKLpAAAAAElFTkSuQmCC"/><element name="castButton" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAQCAYAAAAWGF8bAAABuUlEQVR42mNggAA2IBYCYgkKsBDUHDAQevr06X5KMdRQMJDYvXs3SECLTNdpQfVLwA3cuXMnigCJAEO/xPbt2ykyEF2/8NatW0ECwuQaCNUPNpAZiAVqamqsgTQXuQZu2rQJYqCXl5cQ0LkpjY2Nbuzs7BJQQ5lINXD9+vUQA8PDwyWPHz++4/Lly/uvXr26btmyZUkCAgKiQElWIGYk1sC1a9fCvczNwcEhHxER4T59+vTuEydO7APiqS4uLkpQQ4kycNWqVRADQ0JCxIAu7JgwYUI0CwuLWlpaWtDmzZu3AsVmqaurSwIVsRBj4IoVKyAGurm5iQKdO/fUqVP7Tp48Odfe3t4wNjbWG2jo3o0bN5YAFfES4XUJYFDBvQyKBBmgIX5r1qzZBHTZAh4eHrWOjo6GPXv27ARaqApVI4wvpyxZsgRiIDDsZM6cOTPT19fXLDIy0hvo2n3z5s1L8fT0tF66dOm+uXPnxldXV+vdunVrPz68aNEiSF4OCgqSBUU50GXTgQLSU6dOnbFt27YpIFfPnj17JdCCalA6JeBClNKGHYgFgZgfiDmhYcYL9SaI5iEyYsAAACZV+irLroZ6AAAAAElFTkSuQmCC"/><element name="speedButton" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACgAAAAQCAYAAABk1z2tAAAAGElEQVRIie3BAQEAAACCIP+vbkhAAQDwbAoQAAGlEdGNAAAAAElFTkSuQmCC"/><!--element name="speedButton" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACgAAAAQCAYAAABk1z2tAAAAMUlEQVRIie3OMQEAMAjAMIZYPE0tk8DRgx2Ngpy63fGx3A5MDFIGKYOUQcogZZAySD22EQMdDffUdgAAAABJRU5ErkJggg=="/--><!--element name="speedButtonOver" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEQAAAAYCAYAAABDX1s+AAAAT0lEQVRYhe3QsRGAIADAQGQoKgv3X0o6iiygxf8EuVzrft7BMb8O+BtDwpAwJAwJQ8KQMCQMCUPCkDAkDAlDwpAwJAwJQ8KQMCQMCUPCkNjHKQHh5QpwHQAAAABJRU5ErkJggg=="/--><element name="setButton" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMDY3IDc5LjE1Nzc0NywgMjAxNS8wMy8zMC0yMzo0MDo0MiAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6ODBFNUU0Nzc5MTkwMTFFNUJENkZGQjE5Njc5MjJCOUQiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6ODBFNUU0NzY5MTkwMTFFNUJENkZGQjE5Njc5MjJCOUQiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKFdpbmRvd3MpIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6QTEwNzRFNEIyOTA5MTFFNUExNDVDQTA5NTNBQTYyOUQiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6QTEwNzRFNEMyOTA5MTFFNUExNDVDQTA5NTNBQTYyOUQiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz42GRWFAAABGUlEQVR42mKsqG1ioAQw4ZE7BcT/ofgEqQYwArE2El8LlwEsSOxuIJYG4qVAHAbEXEhyvEA8H4jXAHEMED8G4jJkA0yAuAjqokgcliVAMQj8BeJlQHwB5oUJWLzzEYiXA/EiIP6MJscMxP3IYbAAiH8iKXgHxEZAHAXE8VAXfkCS/wH1KtyAOUCch6QAJHkPiX8L6hoYyITqwRkLbKSmg2QgnogkDgpIZSS+OjT0YWA6ECciGwDicCAp4APiM0C8BBo+p6BRCQMgtXHI0VgAxCfRvCQAxNE4XA6KxkJkA85Ao0UKGr/haE6GxdRaqPgjUBpAT4klSOytQByElBo/wfwMBFuIyQugDHQViX+VmLyADsyIiUaAAAMA/880aR/aKggAAAAASUVORK5CYII="/><element name="setButtonOver" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMDY3IDc5LjE1Nzc0NywgMjAxNS8wMy8zMC0yMzo0MDo0MiAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6ODk5MTI0REQ5MTkwMTFFNUE2NkZGMzQ0QUU3OTNBQzYiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6ODk5MTI0REM5MTkwMTFFNUE2NkZGMzQ0QUU3OTNBQzYiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKFdpbmRvd3MpIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6QTEwNzRFNEIyOTA5MTFFNUExNDVDQTA5NTNBQTYyOUQiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6QTEwNzRFNEMyOTA5MTFFNUExNDVDQTA5NTNBQTYyOUQiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz5CG5X1AAAA/klEQVR42mL8IMJAEWDCI3cKiP9D8QlSDWAEYm0kvhYuA1iQ2N1ALA3ES4E4DIi5kOR4gXg+EK8B4hggfgzEZWCboGFgAsQnCXgJGfyF6rkA0zABi+aPQLwciBcB8Wc0OWYg7kcOgwVA/BNJwTsgNgLiKCCOh9r2AUn+B9SrcAPmAHEekgKQ5D0k/i2oa2AgE6oHp5/ZSE0HyUA8EUk8EoiVkfjq0NCHgelAnIgcC0eA2BrNcJCftwLxHyAOBGI+NPkDQOxIcTTCEtIZaLRIAfEyIA5HczIsptZCxR+BNCN7AVtS/oKUGj8BMT8peQGUga4i8a/i8gsjpdkZIMAALPk3LzrRUTcAAAAASUVORK5CYII="/><element name="castButtonOver" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAQCAYAAAAWGF8bAAABuUlEQVR42mNggAAOIJYAYgUKsATUHDCQePr06X9KMdRQMFDYvXs3SMCCTNdZQPUrwA3cuXMnigCJAEO/wvbt2ykyEF2/1NatW0ECUuQaCNUPNpAFiEVramr8gTQfuQZu2rQJYqCXl5cE0LltjY2Ncezs7CAbeIGYmVQD169fDzEwPDxc8fjx498uX778/+rVqy+WLVvWLCAgIAOUZAdiRmINXLt2LdzL/BwcHFoRERHx06dP33nixIl/QHzcxcVFF2ooUQauWrUKYmBISIgs0IXbJkyYUMnCwmKclpaWt3nz5k9AsXPq6upKQEWsxBi4YsUKiIFubm4yQOdeOnXq1L+TJ09etLe3d4yNjU0BGvpn48aNs4GKBInwugIwqOBeBsWsGtCQjDVr1rwFuuwqDw+PcUdHx+o9e/Z8B1poBFUjiS+nLFmyBGIgMOxUzwCBr6+vR2RkZArQtf/mzZvX6unp6b906dJ/c+fOra+urra7devWf3x40aJFkLwcFBSkDopyoMtOAQVUpk6denrbtm3HQK6ePXv2I6AFS4BsMQIuRCltOIFYHIhFgJgHiIWgmBdKCxAZMWAAABFDD0iNkbKIAAAAAElFTkSuQmCC"/><element name="castingButton" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAQCAYAAAAWGF8bAAAB60lEQVR42mNggAAOIJYAYgUKsATUHDCQ+E8FADUUDBRevXoFEnAAYgsoTSwGq4fqV4Ab+OLFC5CABZkus4DqRxj49OlTsAtBNKkYpg/ZQKmHDx+CBCxBNKkYZCCUBhvIDMQis2fP9gfSKjdv3vx07969/6RgkIFQGmwg35kzZ+omTpwYxcPDo6mmpmaybNmy6devX/9569at/8RgkIFQGmyg8Nu3b39++/bt/9evX1/u3r27lYuLSy87Ozvy1KlTz65du/afEAYZCKXBBvKKiIhol5WVpe3cuXMX0PB/z58/P+3u7m4dFxfnD3T9x0uXLv3Hh0EGQmmwgYJPnjzZvGTJkkpOTk6TysrKbKB3P718+fKKvLy8QUNDQ965c+f+48MgA6E02EChy5cv33z37t3/N2/eXA4ODnYrKipKuXr16s8LFy4sAsprAl1+6vTp0/9xYVA6hNIQLwOxWnFxcd7Zs2ffvn79+q6cnJz5ggULFj148OBXUFCQNVBeCYjN8eWU48ePww0Uef/+/en09HRfYESkAJ3+Z//+/f1OTk7uR44cAbG7qqurCeYgoFp4XhYDBSgwL14FpcNNmzYdunHjxkWQq4FevXb+/PmNQLY4EEsSW9pwQDWIAjEPKJJA4QoNCiEon5WBSAAAryiVoYy0dtoAAAAASUVORK5CYII="/><element name="castingButtonOver" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAQCAYAAAAWGF8bAAAB60lEQVR42mNggAAOIJYAYgUKsATUHDCQ+E8FADUUDBRevXoFEnAAYgsoTSwGq4fqV4Ab+OLFC5CABZkus4DqRxj49OlTsAtBNKkYpg/ZQKmHDx+CBCxBNKkYZCCUBhvIDMQis2fP9gfSKjdv3vx07969/6RgkIFQGmwg35kzZ+omTpwYxcPDo6mmpmaybNmy6devX/9569at/8RgkIFQGmyg8Nu3b39++/bt/9evX1/u3r27lYuLSy87Ozvy1KlTz65du/afEAYZCKXBBvKKiIhol5WVpe3cuXMX0PB/z58/P+3u7m4dFxfnD3T9x0uXLv3Hh0EGQmmwgYJPnjzZvGTJkkpOTk6TysrKbKB3P718+fKKvLy8QUNDQ965c+f+48MgA6E02EChy5cv33z37t3/N2/eXA4ODnYrKipKuXr16s8LFy4sAsprAl1+6vTp0/9xYVA6hNIQLwOxWnFxcd7Zs2ffvn79+q6cnJz5ggULFj148OBXUFCQNVBeCYjN8eWU48ePww0Uef/+/en09HRfYESkAJ3+Z//+/f1OTk7uR44cAbG7qqurCeYgoFp4XhYDBSgwL14FpcNNmzYdunHjxkWQq4FevXb+/PmNQLY4EEsSW9pwQDWIAjEPKJJA4QoNCiEon5WBSAAAryiVoYy0dtoAAAAASUVORK5CYII="/><element name="trackButton" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABwAAAAeCAYAAAA/xX6fAAAB3ElEQVR42u2VP0sCYRzHLwiFUm4oIcUGz4ZMsRqkhhan2hzyBWSvwMXhAsGlFxA46y2JeJpDIeEfDnV1UhdX/+Du5mS/LzyC2F09KDjdAx94nuf3fZ6PPj53CovFQtglgik0habwX+FasxDHhJfwM7xsDjUbcUZc6YB5G69wj7C7XK5AqVSSR6NRfj6f1wD6xWLxBTXKXNMazQhIeYX2SCQSnk6naqfTySYSiZgkSXcAfZpTUAuFQrHxeKwZwSu04NNPJhM1k8m80thHiMQ+A30fasPh8EMUxQiNw0SUeFrhgTjhER6pqio3Gg2FySzC74Y5H2WyyFL/Zpsj9Xa73Xw8Hn9m38aoiZSJIUv9+16vp63DKwz0+/2G2+1+pL6HONCRYc6DDLLUv2U3M7rJkQaazWY9l8u9z2azCo0lHaGEGjKtVquONezbbHSkF7TR52Aw0NrtNhYFdYRB1JCh7BfWYHP6TbVVeIX+arVaq1QqGmBHtd6ulnVk2Qth/SXA/eCf04NdK5fLGjASLuvIYo3RzeIROlOpVLpQKGiAxpc6+1wu68lk8g2XYxuh1eFwBGRZTiuK8m10aVBDhrI4Tus2QoFt4CROiUOdfQ5ZzfmXjEto/gGbQlO4c+EPA9e3TyseGL0AAAAASUVORK5CYII="/><element name="trackButtonOver" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABwAAAAeCAYAAAA/xX6fAAAB3ElEQVR42u2VvUsCYRzHj4awhq5AF3Mol5bSFjMSstYabGusuaVbHBwEsf9DpMDBF4QGB8FBhSYnvQahIfTEtsIg6AWevt94hLCzDoWm+8EHfi/fe74+j/eiCCGU/0SxDW1D2/BPw5FwgGXgBzsSv+xxtgg2wZ4J7C9aNZwBS263O1QoFC673e79qwzm+Xz+ijNo9sUvQVOrhkuRSOS43+8bjUZDj0ajSa/Xe0SYo3fLWSAQSBqGIcZh1dDBX9/r9YxUKnWNOgicYFbCPMhZp9N5UFX1DPUx0EDiG6dgxYqhO5fLXVYqlVtp5lB+BntBaHRqkR9Mc6T+ZrN5r2nahdzNuHBCk6QW+Umr1RKjWDUM6br+4fF4zpGvgwUTM/bWqaEW+aG8M7VJjjRUrVbfM5nM3WAweEa9YWK4wRk1tVrtndfI3Ux0pNtY6LHdbot6vc7GronhLmfUQPvEa7g4/lPxHauGO+Vy+a1UKgkij2o09oZzauULYfQlYPnB38KD/VosFgUZZzicU4s6MO7OsmK4mkgkbrLZrCCowybrhIfzeDxe5c0xjeG8y+UKxWKxm3Q6/YLaZ7KOjzNqoOVxzk1j+GXKnYI1oJqso8rZqtQqExvaH2Db0Db8d8NP8a/SZovcDd8AAAAASUVORK5CYII="/><element name="fullscreenButton" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMDY3IDc5LjE1Nzc0NywgMjAxNS8wMy8zMC0yMzo0MDo0MiAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6NDI4NjU2MEI5MThFMTFFNThBNTVCQzAwNkVGRTM5MTkiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6NDI4NjU2MEE5MThFMTFFNThBNTVCQzAwNkVGRTM5MTkiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKFdpbmRvd3MpIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6RDQ4NDRCRUIyOTA5MTFFNUJCQUE5QjZDREVEQzE3RUMiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6RDQ4NDRCRUMyOTA5MTFFNUJCQUE5QjZDREVEQzE3RUMiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz4JKJvEAAAATUlEQVR42mKsqG1ioASwQOn/WOQY0fhY1TAxUAhYCNiKSw7uGopdQDUvMJKoj3HweIFqBvzHkVBwAbj6wRON2NI7MXmBvgkJqxqAAAMAp0UKoTsi8zcAAAAASUVORK5CYII="/><element name="fullscreenButtonOver" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMDY3IDc5LjE1Nzc0NywgMjAxNS8wMy8zMC0yMzo0MDo0MiAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6NEUyQzA4RDI5MThFMTFFNTk5RkNBMTJBRTJCNTk0OEYiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6NEUyQzA4RDE5MThFMTFFNTk5RkNBMTJBRTJCNTk0OEYiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKFdpbmRvd3MpIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6RDQ4NDRCRUIyOTA5MTFFNUJCQUE5QjZDREVEQzE3RUMiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6RDQ4NDRCRUMyOTA5MTFFNUJCQUE5QjZDREVEQzE3RUMiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz4noIl8AAAATUlEQVR42mL8IMJAEWCB0v+xyDGi8bGqYWJgoI4LcNmKSw7uGopdQDUvMJKoj3HweIFqBvzHkVBwAbj6wRON2NI7MXmBvgkJqxqAAAMA1dcKLnnwOusAAAAASUVORK5CYII="/><element name="normalscreenButton" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMDY3IDc5LjE1Nzc0NywgMjAxNS8wMy8zMC0yMzo0MDo0MiAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6N0MyNzhDNjI5MThFMTFFNUI2RDhCNzM0OEFCRTQ4Q0YiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6N0MyNzhDNjE5MThFMTFFNUI2RDhCNzM0OEFCRTQ4Q0YiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKFdpbmRvd3MpIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6RDQ4NDRCRUIyOTA5MTFFNUJCQUE5QjZDREVEQzE3RUMiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6RDQ4NDRCRUMyOTA5MTFFNUJCQUE5QjZDREVEQzE3RUMiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz5ZA7lSAAAAR0lEQVR42mKsqG1ioAQwYRH7D8VEiTMxUAgoNoAFyXnEAmS1jFRzASMRahlpEgaDOxYY8YQ+bWPhPymxMXgCkWCCwSUOEGAAlB4KoRPmyB0AAAAASUVORK5CYII="/><element name="normalscreenButtonOver" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMDY3IDc5LjE1Nzc0NywgMjAxNS8wMy8zMC0yMzo0MDo0MiAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6NkFGMzMzMEQ5MThFMTFFNTkzNTlBNzRCNDZGNzAyRkQiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6NkFGMzMzMEM5MThFMTFFNTkzNTlBNzRCNDZGNzAyRkQiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKFdpbmRvd3MpIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6RDQ4NDRCRUIyOTA5MTFFNUJCQUE5QjZDREVEQzE3RUMiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6RDQ4NDRCRUMyOTA5MTFFNUJCQUE5QjZDREVEQzE3RUMiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz5VFr2VAAAASElEQVR42mL8IMJAEWDCIvYfiokSZ2JgoL4LSAIsSM4jFiCrZaSaCxiJUMtIkzAY3LHAiCf0aRsL/0mJjcETiAQTDC5xgAADAMKwCi5/sTkCAAAAAElFTkSuQmCC"/><!--element name="volumeCapLeft" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAMAAAAeCAYAAADpYKT6AAAAFElEQVR42mP4//8/AwwzjHIGhgMAcFgNAkNCQTAAAAAASUVORK5CYII="/><element name="volumeCapRight" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAMAAAAeCAYAAADpYKT6AAAAFElEQVR42mP4//8/AwwzjHIGhgMAcFgNAkNCQTAAAAAASUVORK5CYII="/><element name="volumeRailCapLeft" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAQAAAAeCAYAAAALvL+DAAAAeElEQVR42tWKQQqDMBBFB3cFt9oQQ0wniW51b5f2ti30ZLX1AN+ZQA/hhwfz/zw6eZrmmoWn8NUyCh9jLJzzoLY1L2sd+v6GEBikmh7MCTHmYvyYI1LKBeo69/Y+SBkKtCz3SaztPxKAal0fs5ry2Emjo3ARajpNDtqHL/b2HUUVAAAAAElFTkSuQmCC"/><element name="volumeRailCapRight" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAQAAAAeCAYAAAALvL+DAAAAeUlEQVQYV9WKOw7CMBBEV3RItAmWYzlmbUMLfSjDbUHiZASFfpj1LTLSW+18RLarrjt+yZPUFoQQ4ZwHgw+5SEqKcTzB+4C+dy/JuUK1wAouVimlwlDNtvgxOMOIMWEYwrsFZtgu03S/Cp/Vmnl+3ADshOdA9s1sSn8goC/6ib5oHgAAAABJRU5ErkJggg=="/><element name="volumeProgressCapLeft" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAQAAAAeCAQAAAChtXcIAAAANUlEQVQY02NkgAJGOjH+9zEkAxm/JrzJ/wYSufTxLx9Y6shHBghj10SGPKji9RMYkhjp6EIAcaIN1SJ2FnYAAAAASUVORK5CYII="/><element name="volumeProgressCapRight" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAQAAAAeCAQAAAChtXcIAAAANklEQVQYV2NgoCP4//F/H5hx5/+z/78mABnn/5//f+kzkHHkPxCCGLv+A+FEIGP9p/UgFXQFAHkZGwN2fDIsAAAAAElFTkSuQmCC"/--><element name="volumeRail" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAFAAAAACCAIAAABANcwGAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDIxIDc5LjE1NTc3MiwgMjAxNC8wMS8xMy0xOTo0NDowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKFdpbmRvd3MpIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOjFDMDhFNjM1MDM4NzExRTVCM0Y5OEIyM0VBNkZCMDk1IiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOjFDMDhFNjM2MDM4NzExRTVCM0Y5OEIyM0VBNkZCMDk1Ij4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6MUMwOEU2MzMwMzg3MTFFNUIzRjk4QjIzRUE2RkIwOTUiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6MUMwOEU2MzQwMzg3MTFFNUIzRjk4QjIzRUE2RkIwOTUiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz5Jwl3lAAAAFklEQVR42mI0s3FgGEmAiWGEAYAAAwBTawC2yg0eOwAAAABJRU5ErkJggg=="/><element name="volumeProgress" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAFAAAAACCAIAAABANcwGAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDIxIDc5LjE1NTc3MiwgMjAxNC8wMS8xMy0xOTo0NDowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKFdpbmRvd3MpIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOjJBMkU1MjdDMDM4NzExRTU4OUY2REExQjhEOURBRDE5IiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOjJBMkU1MjdEMDM4NzExRTU4OUY2REExQjhEOURBRDE5Ij4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6MkEyRTUyN0EwMzg3MTFFNTg5RjZEQTFCOEQ5REFEMTkiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6MkEyRTUyN0IwMzg3MTFFNTg5RjZEQTFCOEQ5REFEMTkiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz7lBYb1AAAAFUlEQVR42mI8wzCyANMI8y8DQIABAIUBANB8q1pJAAAAAElFTkSuQmCC"/><element name="volumeThumb" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAA0AAAANCAYAAABy6+R8AAAA5UlEQVQokZ2SQWrCUBCG/39e7CnctNsKXkIw5YHdSE5gKeJ5KkjjDUqh0kWE9BASupX2FqYmMy7klWIXJn6rWXwfDMwQf3hZZbedKzch5Q7ANYCtmWb7nzodj+LP4DEMq3X+BHAqIsQJqmoGe74fDh5/o7fsY+6cTE/l/3E9Hw0HM76+r3tRJ9qIiJyPVKt91RcXuUmTAABE5OiTjJsEAZKxALhpEwHoCoBtq8TwLWaWtYpouZR1uVRVbeKrqu6qMpXE+8IMi0abGRaJ98XlHxE4Hto9mDEm0YXhC7R8V5Vp4n0RvAN372T8pAc16wAAAABJRU5ErkJggg=="/></elements></component><component name="display"><settings><setting name="bufferrotation" value="45"/><setting name="bufferinterval" value="90"/><setting name="fontcase" value="normal"/><setting name="fontcolor" value="0xffffff"/><setting name="fontsize" value="12"/><setting name="fontweight" value="normal"/></settings><elements><element name="background" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAFAAAABQCAYAAACOEfKtAAAAMElEQVR4nO3BMQEAAADCoPVPbQ0PoAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAIAXA2RQAAEpFbdMAAAAAElFTkSuQmCC"/><element name="backgroundOver" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAFAAAABQCAYAAACOEfKtAAAAMElEQVR4nO3BMQEAAADCoPVPbQ0PoAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAIAXA2RQAAEpFbdMAAAAAElFTkSuQmCC"/><!--element name="capLeft"      src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAQAAABQCAYAAAA+7tSfAAAAFElEQVQ4jWNgGAWjYBSMglFADQAABVAAARxvLc8AAAAASUVORK5CYII="/><element name="capLeftOver"  src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAQAAABQCAYAAAA+7tSfAAAAFElEQVQ4jWNgGAWjYBSMglFADQAABVAAARxvLc8AAAAASUVORK5CYII="/><element name="capRight" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAQAAABQCAYAAAA+7tSfAAAAFElEQVQ4jWNgGAWjYBSMglFADQAABVAAARxvLc8AAAAASUVORK5CYII="/><element name="capRightOver" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAQAAABQCAYAAAA+7tSfAAAAFElEQVQ4jWNgGAWjYBSMglFADQAABVAAARxvLc8AAAAASUVORK5CYII="/--><element name="bufferIcon" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDIxIDc5LjE1NTc3MiwgMjAxNC8wMS8xMy0xOTo0NDowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKFdpbmRvd3MpIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOkYxMEU3Mjk0MDM3NzExRTU4NTMyQzhBRjNGODgxMEE1IiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOkYxMEU3Mjk1MDM3NzExRTU4NTMyQzhBRjNGODgxMEE1Ij4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6RjEwRTcyOTIwMzc3MTFFNTg1MzJDOEFGM0Y4ODEwQTUiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6RjEwRTcyOTMwMzc3MTFFNTg1MzJDOEFGM0Y4ODEwQTUiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz5cRJ/oAAAF1klEQVR42uRbW2gcZRT+d3Zya9Im9hKTxnpvpFh9iFhBi1ARtaLUqg8qaEEEEX0RVNAXfVFQEBXUR18ULViRQqlFQbyBWqsiDWJtY7St1jba5qJNXLMznmO+sWeP/+xOspfM/DnwMclmNzvnO5f/nH/OnwvD0NRZeggDhNXAuYTTCUsIiwke4U/CBGGEMETYT/iesJtwpJ43l6sTAWsJmwgboHSuDLwKf2cy3iW8Sfg6zQS0E24n3BqjtDdHAiS+I7xKeAUekwoC2I23EO4idFVQzKuSgAgnCC8TXiKMzhcBObj5w4SlZZQZRSwfQHwPE47BghOCRM4J3YTzCf2ECwjrCctjSGA5TngUXhE2koAzCU8iudlubpywnbCH8E6VHnYT4XKE19IYIj4l3E34oREEXEN4AlbTN3OQ8DZha7WuaRFW/h7CnfASTQJ7072EbfUigL/oQcS6Vnya8CJc8a86L6uthAcIjxOaFQl8fY7wSNKQSEpAE6y+0aL8dmTlYdNY6YeiW4Ty0fUNhEShFgSw8s8iDrXyzxBeN/Mr9xNesJCwi3B9tQTkYiy/j/AUYdCkQy7FsjggSPCSfLDSm+4jXIt4ivA5ckFalGf5gnAz4X2ll8wPs/aADVjqpOV5WXuIMGnSKe3oK3Q4hHFJMY6AlUhscqkbgvK/mvSLtjz/XEwaAvzmx8CmdP2nM6K8EdaWRORtb/Qtr3HMX6Rc5nnCXpMtCS01Ar8WlPOADlRT0vI70YqaDBJgVD7wK4UAZ9JOwRRXda+Z7EpgKZnzcQS0ETYL5UMkwoMZJkC6fESCH0fADQiBSPnDhLdM9qVoWR18YykYNqrY35Wkls5oKPyPgDVY+yPr/07YYdyRovIAL8oFEQHrhfJ8fQ+bGq5IaCGhhIABlfwGjXtiDQMfe2594g1jaHhck6KqCdgDcj42FkLhKnuNuxKoOsBnAs5Ra+WQ4wTIJinvw/2lBxxaaAQsVw3CsYVGQJf6wwmHCQgVAR4T0KJ2TCYXIgEyBFwmwCgC/l0GQ7OwJKe7wZPwgAhtjivvCfxHQChK4VbHCSjZIPGQ9QPRB3Q5TIB+fB/yCyPC+oxlDhOQVwQETMDPqhXudZiAZuUF0z5KX7kM9jlMQJN0f8LfPpofWQj1O0xAq1oGC+wOPGfziyCBnwhd7KDyi5QH8P5AMdoRGlS1wHkOWz/CVLQssHxpSrfE1sETXMr+S0zpJNtJSQBPVx0VJPBT4SscIoCV903pTFOJB7DiH6vlcB1ixoXqr1NZf0JWRpF8YmaGC6I8wBslVzpAwGnoeOXyN24jgB+EfqD6Ap4JXJHxwqdbWX9U1j366fCHYCcigZPHVRkmYIWq/AKjBjg1AbwZskMtiZeYmRG5rMkyuL+0/ohRozK2ERmeuBpWoXAjYVXGip4+pfyksYzv2ghgpbfiA/Jp8SZk0yzE/SqlfICmzyQhgIWfDm9TocDHXDbjC9Lc75+NIk7GPitfmA0BLPyI7CPlBfwU6TYUFmm0/GpR8eVE3I/FFgkJRmVvQXMkcwI3TztxTYN0wPKLlMHYk38sq2CCYWleCu9AgxQoIvgw0555Vr4XyocKnPAOmAq73knH5fNIghcqAvj6DeEz0/gnSm1Idt3KMIzf0N9UVG62J0auJlymCAjQXHAp/ZWJGUmtcaI7g3CWKR1+jJTnsPwpcaMwhyMzPE90HZKO/nKOuW+RQKfqsJ3Fivegt9eeyEbYj3sw9SQgajD4MMJKizcEqCH4jN8RU/28QQ++rxehqL8vRJbfNxfSqz03uBYdY0vMjfH1DzMzZH0ciWkMBBVgNQ+9ejOs3IF1vAOKN5X53wUQPOch7locnGTlB7BUtli8weYhlX4v9/kQneshYLqqzYIaHp1thkesMaXzxkEVP9u8iZU+XKtkW6/D09yG9mOZWlzGE5IqfRTl7FjNt4sacHy+HWt1pzl1ZL4VcZ+HCxeAKXPqSO048sZUPW/uHwEGADvAId9CNPZqAAAAAElFTkSuQmCC"/><element name="errorIcon" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAFAAAABQCAYAAACOEfKtAAAAMElEQVR4nO3BMQEAAADCoPVPbQ0PoAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAIAXA2RQAAEpFbdMAAAAAElFTkSuQmCC"/><!--element name="errorIcon" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAAIdElEQVR4nOWbXWwU1xXHf/fO2hvjtZfdNQ4fAcSHCQgJtRWVoJF4adoG+tDkgVQiRaSoKQUJKFXSoOY9DaFpColQUaMWRMNDKiVKGwxEfUkUkZZErUpVYcCUGoMBy7Pr9Qd48c49fcBrz+yOjfHODDT9Syv7/ufOvf97du+dueeeowgZV9I8UgurLVgGLEWxBJgJJC1FAsARBoA8cB3hPNDmwNnb8OkjWa6EqU+F0WhXkpXxGjYgrLMUj1bTliOcQ9FaGObo7DyfB6WxhMAMcCFN43TNFi1831IsC6pdNxzhrFH8rtdwsCVLXxBtVm2AjiSphMVOpdihFakgRN0NRsiJsH/AYd/8PLlq2qrGANpOs0kp9mpFZrxKRjACf0fxMYZ/OsKFotB5S9G7KEce4GKKZJ0wPaaYqzWLlWIFwhoFX9YKPUHbtggvZLIcBsxUBjElA3Q20jKthkOW4mvjCCsaOCGGt28aTk71W+pIkpqm+ZbSbNCwVitifvUc4dTNYTbN7aP9Xvu4ZwN0p3naUrxlKRrKrxkhJ4o38rc4sHCQG/fa9kT4dz0PJ+vYpoTtflPNEfod4QfNWd65l3bvxQDaTrPX0vykonMYwvBKFl4PanEaDxfSNKZhF5rdFjxUocXwy0yWF5jilPBFK8TtNEd7m5Dyj91Ea0eShYF1Nkl0JFloN9HqqynN0VaIB9JRK8TtDCfKO8lmKPRk2AHjL1IRQPdk2J7NUKgwQoYTkzHC3aaA7slwJKbY4CYd4dpt+M4sm8+qkh8QrmX4ai28bylmufmicLTJZiNTnQ49Gfb4WPZc13TmByE8SHSlmGdnaPOZDq9OdN+4v4DuNOtrtXdFdYQLfUOsWTDI9akKvZFiRdziH37XhuF7M3p4e6ptX6pnZuNDfGTd2W+M4rbhu+M9HXzn7+Uki2KKt9ycI1wdEr5ezeDDxoJBrg8JjzvCVTcfU/zmcpJFfvf4GUDX13BYKxpLhBGGC0WenJOlM2DNgWNOls5CkSeNMFzitKKxvobD+Iy3grDTbLLgMTdn4PkwdmJhYXaezw087+YseKw7zbPldT0G6EiSUtq7aDiGY002+0NRGiKabPY7hmNuLqbZ05H0vkV6DJCw2KmhqVR2hMEh2Bqu1PAwBFsdYbBU1tCUsNjprjNqgDZoUIodnhaEPf8L8348zMnSKfCKm1OKHRfSY+vbqAEyTfzIvckwQm8W9kUjNTzkhP1G6C2VtSI1XbNltFz6a8EP3TeKsC/sjU0UaMnSJ8q7hlmwhZGxa4AbKVZrWFyqYIRi7jYHIlUaIvK3OGCEYqmsYdGNFKtH/oeYZr37BgPHFw/QHa3M8LBwkBsGjru5mOZpGJsCT7gvGuH3EWmLDEY4UkY9AaCvppnrdl0boZiHE5GqiwDZLCfcb4eWYklXinm6Bla5Kwp89kVY/MqxFPoF7/Y9plitteJLnpqKv0SqLEoo/uouasUKrfAeYhiHc9Gqig7lY1OwTCu8zg0RzkYrKzqUj03BfC2UuZGgI1pZ0cFnbE0avP79W2rstTEMDBcZmuByf5h9D+qKA5qELh1Rl1A6rgoL2qIw7kUnQF++D3yebg2Ru7TzDgPjXRPCNb6fm1yPBCeM4mKKZJgilvVjG/H/ph2FHWbfixoqjvP6NWXzrk6YHqYIABRZP3qo4M8HhaSuMMCAVnDNzcSIxOd/tZwwUAjb46wVc8uoHi1ljwYVUnSHG2Iq435Ewn/8lo9NoEML3pcDbVUX0zMZiOKyD/2fsPvVZQcmAme1Ec7gZT2bozAgiksVpL9RgoXyjs0IZ3RR+NRbh5VtVAY/BAkjXCznRML9BbRBg4KVbq5oRjZ+doZz7gPF7rTXQ/RFQHea9WWHvOdhzCPkcYBoxcbIFYYMnzEdhxEDOIY/eCrD2vYEzRFpCx3tCZo1rHVzpTFrgOYcpwxj81IrYqlatkUrMzykatnmjjAzcLE5xykYmwLGgYPum5RiR1iL4ZUGltgZ3rUz9I183r3S4H1EBQW/E6+RsRpwnQz1Gg6Wn6DMSLMraEFdGZZOq+W0pXjKUjSMfJ6aVsvprgxLg+5vRppd5SdevWbsyx41QEuWPpGyozDN7qAjwOLwslaVGy6tSMbh5SD7up5kAZrdbk4U+93bYs92eMBhn4GeUtmCukQNbwQpCnh8gmvfDLKjmhretKCuVDbQ013wHpN5DDA/T65oeNHNWbCuJ8P2IIVFgZ4M2y1Y5+aKhheX9Xu33BUOkeYshxy5s0K6Kr3WlfS+RVWBP09w7cMgOuhKslLDa27OEU41ZzlUXtfPI2RuDrPJiGueKGriMf7U2UhLteIK8JKRSs+PEfIFeKna9jsbaYnH+KNW1Lja7rs5zCZ84gV9XWJz+2gvCs+5OUsxs76W45fqmVmNwNk2Z4dglSO85wgDI5/3hmDVbLs6l/yl+jsafQImn5tKJDl2mr0+gZJtXSnmVSM0DEwQKPmLie77vw+VvZtX2Jy22ewIJ92kpZgVh08ehKdDT4btcfikfPCOcPK0zWaCCJufMFw+zQf3I1z+epIFdpoPqg2XDzJh4udZ+FVECRM70fwsiISJ4FNmhH35Ar8OOmWmPUFzqpatSrHzfqXMjGIySVNAa1E4esvhw2qSpuosvhFTPAOseyCSplzQ3WmejSlenUTa3N8QPhbFGcfQ7kBnLkd2OQy8A7XLG0k0KFJxi4ctRYsSVqBYo+Ard0ubKwo/HXnDiy5tzo2OJKlEDT8eL5srDBgh5yjevDnM6/czcdIDV+rsZksFv68HcIQ2o/jtA5U664euJCvjMTYqxbc1/okKk4WBiyIcKxQ58kAnT48HV/r8cuBRpVgiMBNFyoK6kQVzAEWOO0+R88A5B/4VRfr8fwEECY9cFr55XAAAAABJRU5ErkJggg=="/><element name="playIcon" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADwAAAA8CAYAAAA6/NlyAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAA3ZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDIxIDc5LjE1NTc3MiwgMjAxNC8wMS8xMy0xOTo0NDowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDo0ZmE4ZmU1YS1lZmEyLWI0NGQtODNhYy1lYzE1NWI4MzNmYjEiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6REQ0NEVCQzYyRjg3MTFFNUEwMTZDMDZBRjlEMDUzNzIiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6REQ0NEVCQzUyRjg3MTFFNUEwMTZDMDZBRjlEMDUzNzIiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKFdpbmRvd3MpIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6NGZhOGZlNWEtZWZhMi1iNDRkLTgzYWMtZWMxNTViODMzZmIxIiBzdFJlZjpkb2N1bWVudElEPSJ4bXAuZGlkOjRmYThmZTVhLWVmYTItYjQ0ZC04M2FjLWVjMTU1YjgzM2ZiMSIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/PgyDlFoAAALYSURBVHjaYmRgYJjJMIIAE8MIA6MeHvXwqIdHPTzq4VEPj3p41MOjHh718KiHB8jDXFxcTOvXr7f68uVLPBDHbdiwwUpBQYFjsHqYkdLu4bp166wCAwN1kMW+fv36c/r06aerqqqu//79+/+w8jAoZrm5udmxyT18+PBNWVnZsVWrVr0YNh7+//9/GgF5hoMHD97JyMg4cfPmzW/DvtBiZGRkcHBwULl48WL41KlT9UB5fkSU0uzs7KxZWVkWd+7cCUlNTZUdMdWSpKSkwKxZszyPHz/uZmxszDti6mELCwsFoKdDFyxYYMLPz88yIhoerKysLPHx8UZ3794NKygoUBwxLS1hYWGe/v5+V2DB5m1vby84YpqWenp60nv37g1evXq1hZiYGNuIaEszMzMzhYSE6AHr7LD6+no1JibqOpHmDQ9Kwe3bt1/l5eUd2bFjx5sR0VtSVVUV27ZtW+CmTZusqdFoGRLdQ2BrjdHX11d70aJF5iOqP+zh4aE6OgAwnD0MLLhuU2oGy1DwKLAm+L9ly5brcXFxJ4d9DIOqJR8fn/V+fn5Hvn379m/YxvCHDx++TZw48VRTU9Otf//+Uc3cQedhoOf+rV+//gqwsXHu2bNnv6ht/qDy8OXLl5/m5uYeO3jw4Hta2TEoPPz27dsvbW1tx/v6+u7T2q4B9fDv37//rFy58lJOTs6Fjx8//qGHnQPm4ZMnTz4AJt8Tp0+f/kRPe+nu4RcvXnysq6s7Nnv27McDEdB08/DPnz9/z5s371xJScllatSng9bDoIH4w4cP30lLSxseA/GgeSRccqCplqioqE329vb7BoNnqeLhXbt2YTTogUn2Z09PzxF1dfUNK1asGDTzSuC+NQOFQzygUYilS5dauLm5qYKS7549e24XFBSce/DgwY9BOZjAMLqadtTDox4e9fCoh0c9POrhUQ+PenjUw6MeHvXwqIcxAUCAAQCqPB8zXnyNqgAAAABJRU5ErkJggg=="/><element name="playIconOver" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADwAAAA8CAYAAAA6/NlyAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAA3ZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDIxIDc5LjE1NTc3MiwgMjAxNC8wMS8xMy0xOTo0NDowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDo0ZmE4ZmU1YS1lZmEyLWI0NGQtODNhYy1lYzE1NWI4MzNmYjEiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6REQ0NEVCQzYyRjg3MTFFNUEwMTZDMDZBRjlEMDUzNzIiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6REQ0NEVCQzUyRjg3MTFFNUEwMTZDMDZBRjlEMDUzNzIiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKFdpbmRvd3MpIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6NGZhOGZlNWEtZWZhMi1iNDRkLTgzYWMtZWMxNTViODMzZmIxIiBzdFJlZjpkb2N1bWVudElEPSJ4bXAuZGlkOjRmYThmZTVhLWVmYTItYjQ0ZC04M2FjLWVjMTU1YjgzM2ZiMSIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/PgyDlFoAAALYSURBVHjaYmRgYJjJMIIAE8MIA6MeHvXwqIdHPTzq4VEPj3p41MOjHh718KiHB8jDXFxcTOvXr7f68uVLPBDHbdiwwUpBQYFjsHqYkdLu4bp166wCAwN1kMW+fv36c/r06aerqqqu//79+/+w8jAoZrm5udmxyT18+PBNWVnZsVWrVr0YNh7+//9/GgF5hoMHD97JyMg4cfPmzW/DvtBiZGRkcHBwULl48WL41KlT9UB5fkSU0uzs7KxZWVkWd+7cCUlNTZUdMdWSpKSkwKxZszyPHz/uZmxszDti6mELCwsFoKdDFyxYYMLPz88yIhoerKysLPHx8UZ3794NKygoUBwxLS1hYWGe/v5+V2DB5m1vby84YpqWenp60nv37g1evXq1hZiYGNuIaEszMzMzhYSE6AHr7LD6+no1JibqOpHmDQ9Kwe3bt1/l5eUd2bFjx5sR0VtSVVUV27ZtW+CmTZusqdFoGRLdQ2BrjdHX11d70aJF5iOqP+zh4aE6OgAwnD0MLLhuU2oGy1DwKLAm+L9ly5brcXFxJ4d9DIOqJR8fn/V+fn5Hvn379m/YxvCHDx++TZw48VRTU9Otf//+Uc3cQedhoOf+rV+//gqwsXHu2bNnv6ht/qDy8OXLl5/m5uYeO3jw4Hta2TEoPPz27dsvbW1tx/v6+u7T2q4B9fDv37//rFy58lJOTs6Fjx8//qGHnQPm4ZMnTz4AJt8Tp0+f/kRPe+nu4RcvXnysq6s7Nnv27McDEdB08/DPnz9/z5s371xJScllatSng9bDoIH4w4cP30lLSxseA/GgeSRccqCplqioqE329vb7BoNnqeLhXbt2YTTogUn2Z09PzxF1dfUNK1asGDTzSuC+NQOFQzygUYilS5dauLm5qYKS7549e24XFBSce/DgwY9BOZjAMLqadtTDox4e9fCoh0c9POrhUQ+PenjUw6MeHvXwqIcxAUCAAQCqPB8zXnyNqgAAAABJRU5ErkJggg=="/--><element name="playIcon" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAFAAAABQCAYAAACOEfKtAAAAMElEQVR4nO3BMQEAAADCoPVPbQ0PoAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAIAXA2RQAAEpFbdMAAAAAElFTkSuQmCC"/><element name="playIconOver" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAFAAAABQCAYAAACOEfKtAAAAMElEQVR4nO3BMQEAAADCoPVPbQ0PoAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAIAXA2RQAAEpFbdMAAAAAElFTkSuQmCC"/><element name="replayIcon" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDIxIDc5LjE1NTc3MiwgMjAxNC8wMS8xMy0xOTo0NDowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKFdpbmRvd3MpIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOjI2MzI0RUZCMDM3NzExRTVBOUNCQkEzMTg1RDQzQzUzIiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOjI2MzI0RUZDMDM3NzExRTVBOUNCQkEzMTg1RDQzQzUzIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6MjYzMjRFRjkwMzc3MTFFNUE5Q0JCQTMxODVENDNDNTMiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6MjYzMjRFRkEwMzc3MTFFNUE5Q0JCQTMxODVENDNDNTMiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz7WQzNgAAACV0lEQVR42sSXO0gcQRjHd89TOE7RJodX2SgIimJlElKJNicWFgraKFhpIGBjZW9jI2hvY0gIIY1WvhrRRhRE8Y1vEB+cCqKewvqf8J8wGfbudm9d94MfMzs332Pndr75xrQsywhSQkbAEvaob4Ji9m+B9RYrEM6wMhHQDsbBNkiBJElxbJxzIo4iEN+AQj44ASvaeAQMgivLuVxRJ6LZ+g99oEQxIMc+gwNl/BAMgSYQByaJc2yIc6Qc0EZOAXSDZz4fgQ4QyvRGJMS5R9R9pi1XAfQq/R+gyIFjnSLqSulyE4CUYS6xkSNCd4S2UuCj+rupJaISftFSZsEk+0/gN7jIcbv+BG1gH1SDR7tdUJrlyx71sBLF4Ix2BtKtgHzrQrCp71gwBpY9JK4u5olzUPY3d3h4o1wIg1OuQosYe++z4AX8Yr85qMNonm19UAFssK3QAxD9dbDkcwDXbKP6cVwAqpRT7+G9C5LHf8nBMGI++vzA9s7uG9hhW+NjALWqLz0A+f83+hhAA9tFu1ScYJI4BwU+JCJRnFzSR5PdaSgqomNO6PMhgK9KUZNnF4Dgm1JSxd7QeUwp6XrT1QMyX69x4jSfvToXKztDm6uqzXQKVeCeChM04MX5BG3d07aRLQBBMysYIbMsOt06j1NXVkOJbCWZ3a6QK5EE/SDqwHGUc5PKmyfs5poO7oaV4DuoU25Af8Acz45LJcNVc5+3KjemVdAJtpxcTDKV2T1g18XFZJc6Gct40+XtWBSXX1hMfALlLGSF3IA9ZtMpsODkrmgGfT1/FWAArChULSvgZ9oAAAAASUVORK5CYII="/><!--element name="replayIconOver" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACQAAAA0CAQAAABI31KIAAADTElEQVR4Ae2VX2xTZRjGH1iBzDMrU6lxLdOFhLJ/CepwTWCJiUSTDTdilikxJmAo2GlJ9I7EsCgkw6jRG5ALtZNJy7QDiwxK0dZllSypssqatCHIMKdzM4uEnUUrtj2P57uAULNzOtltf8/Nl3OevHnf73u/70WJxVKiRAWqcD/KsGjsvyScb6EBZizFoth4nX9zJNn6KtZCwhLcNU9NcpJasPw3o80vogbl/y/YUkiwoRHNcMsUSvMGlX/6zz3SCiuWLzSIGXVbnN5gXJ7566b6K29J5ix///PwMWk9ylGUZVj93M5o6qZ6g9OUeY0TBZI5x9ggKlGEFbDvP6Jkp3lFR8PX93yEOpQXy6a2L6Bo9suaTv/2tv/ZPdLey7ylWKZnYEULLFhWbG+q3/f8waSmiPLKB3gSVkh4OkmhsdyHkZoO2Bay0eYtzulcggl+PVXTiYdggmBjgpf42XjzDqwRRy+OAo/eVwNJP5+675Pj/JkhZW0XVt7uFvvQePte1ONezSFclo4d0fjFH7FOr9Ol9l1X1Yv8idt6Ybmj6SRUofL2XSt76Zm57DVeVdt36eVkO3o2xhi9k9gAE/TzXn88LXxHz8KGeWkMyaMc5T4/rDDCus8vfCEZjZgXx0gmyijb3JBghNTmFr6RDByYl5ZofpjDfKANJhhR9mCr8P2QR4tOoG/zYYa57vligVa1Ct93uoEcJzLneZ4vvIEKGHFPx+vCd0K3tMZP5SCDfNeLKhjx8HvHhO8T3c22vRMc4hCDaTQZFGdC07m08O3XPX5p8+6AeooX2F3QkAUsgaW79wJPMaBu3g1Jr9XqD6ZO8iTHlYY7rkhBmJUNXZdmhedgCvX6w8C8yenLDTLE+JS9ExaY/lOUxd4ZnwpxkL7cJifMhs/Ids8Av2SEE4pWYBOqIKEMJlTAiqbu3gklov0d4HYPqo2H03LUugI+HucZznAs/fFXW92VbWu2bnvzsH8sPcMz2h8fXzuNWs1Z/KntOtKX9dLLMK9wjnlmOautwhTf+nIvf446zYUFPf5P7OxJ9atfsFD97Ek97kS1TjZ64+gxpyt4QD6U8age9VDmgOwKbnChXn9wFxuQDrRocmir1ai4y+lfokSJfwEhAcqxd5L4JgAAAABJRU5ErkJggg=="/--></elements></component><component name="dock"><settings><setting name="iconalpha" value="1"/><setting name="iconalphaactive" value="1"/><setting name="iconalphaover" value="1"/></settings><elements><element name="button" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACwAAAAgCAYAAABpRpp6AAAAxklEQVR4Ae2YsQ3CMBBF7+yIximQSERSMgYNI1AxJgswAaMkLREpEnQ2Z6Chooqwpf+k65+evhtzXW8LIjrp7fUcpcmod9U7v2Sbpjm2bVtaa5kSRERC13V13/ePIpatqk05zzOHEChFWImOKnyIwk7EMyXMJyTrOUOZAeGlKd4byUtYCZjEN9gwCuPRYRKYBCbx18JLJ0bh3IQJk/gFHh0Ko3BWwqOID8YYpoTx3ofoap0r18y0WymspCo7DLf7NE2X7L5bnyz7UgI6sO7WAAAAAElFTkSuQmCC"/><element name="buttonOver" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACwAAAAgCAYAAABpRpp6AAAAzklEQVR4Ae2YMU7FMBAFx04osQvyRQIX4nfcgRZOAxW3oMqRkhKbBkWyjVfiCiD7a0dKPxq9dZHxdLq9Al6AB8DRJl/ACryOwPM8z0/LsvhhGCwNklLK27bd7fv+LcLnabrxx3HYUgotYoyx4liFH0XYpZQtDfMb0orrSGeo8L8Il9Jd4dL5JFRYN6xHp5PQSegkLuwd/uPEWrg3YXQSenRaWAtfVOGYUs62QsPkiriK8Brj571z3ot0q7IxhgB8iPBbCMHU7wxcN/679f0HQzRYj4Eg/3AAAAAASUVORK5CYII="/><element name="buttonActive" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACwAAAAgCAYAAABpRpp6AAAAwUlEQVR4Ae2YsQ3CMBBFD8e0CVESUcFMpGMKapgAKvagymKWiF3RxMe/IUDn6J70I5dPX98u4odhvyWiG3JCdqSTiEzI3eNz7fv+0nVdW1WVI4VkEEI4IB8RHjXLCg6II4TPXmbgADOTZhwQV0+F4ekPmDBzcQ2zTcKEC9+wXTqbhE3CJrGyd5jpp1jDxb0SNgm7dNawNbyqhudlydkBUkwG4irCU0rzsa6bVqt0BinFN44vEX7EGDfIiHOj/Hfr8wvCZ0/Xf6TpeQAAAABJRU5ErkJggg=="/><element name="divider" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAQAAAAgCAYAAAA1zNleAAAAD0lEQVQoU2NgGAWjADcAAAIgAAEeEYatAAAAAElFTkSuQmCC"/></elements></component><component name="playlist"><settings><setting name="backgroundcolor" value="0x3c3c3e"/><setting name="fontcolor" value="0x848489"/><setting name="fontsize" value="12"/><setting name="fontweight" value="normal"/><setting name="activecolor" value="0xb2b2b6"/><setting name="overcolor" value="0xb2b2b6"/><setting name="titlecolor" value="0xb9b9be"/><setting name="titlesize" value="12"/><setting name="titleweight" value="bold"/><setting name="titleactivecolor" value="0xececf4"/><setting name="titleovercolor" value="0xececf4"/></settings><elements><element name="item" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAABMAQMAAAASt2oTAAAAA1BMVEU8PD44mUV6AAAAFklEQVR4AWMYMmAUjIJRMApGwSgYBQAHuAABIqNCjAAAAABJRU5ErkJggg=="/><element name="itemActive" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAABMAQMAAAASt2oTAAAAA1BMVEUvLzHXqQRQAAAAFklEQVR4AWMYMmAUjIJRMApGwSgYBQAHuAABIqNCjAAAAABJRU5ErkJggg=="/><element name="itemImage" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAGAAAAA2CAMAAAAPkWzgAAAAk1BMVEU0NDcVFRcWFhgXFxknJyozMzYyMjUlJSgrKy4jIyYZGRssLC8YGBobGx0kJCcuLjAiIiQaGhwjIyUpKSwkJCYaGh0nJykiIiUgICIwMDMqKi0cHB8lJScdHSAtLTAuLjEdHR8VFRgxMTQvLzIvLzEoKCsZGRwqKiwbGx4gICMoKCofHyImJigmJikhISMeHiAhISRWJqoOAAAA/klEQVR4Ae3VNYLDMBQG4X8kme2QwwzLfP/TbeO0qfQ6zQW+coRxQqYl4HEJSEACEvA8NQamRkCoF40kNUxMgC3gc0lrtiZAB1BKuSOPDIzcXroB0EtL3hQXuIHLNboDC+aRgRnQ6GUAjtBEBmrgdcwA/OCyuMATraOvBiB3HBQTOJ8KZp5QwwXoA3xFBdrVjpPnHVgBfQfjqMChZSoAugDMwCsqUMFeAHwEwMFnXKDkshGAz5YAEOIC2fpbAqhUAMDG4AcO3HUAahkAHYykOQATC6Bsf7M7UNotswLwmR2wAviTHVAAHA2BMXCWIaDC7642wIMSkIAEJCABxv0D1B4Kmtm5dvAAAAAASUVORK5CYII="/><element name="divider" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAANIAAAABCAIAAAAkUWeUAAAAEUlEQVR42mPQ1zccRaOIzggAmuR1T+nadMkAAAAASUVORK5CYII="/><element name="sliderRail" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAABCAYAAADErm6rAAAAHklEQVQI12NgIABERcX/Kymp/FdWVkXBIDGQHCH9AAmVCvfMHD66AAAAAElFTkSuQmCC"/><element name="sliderCapTop" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAKCAYAAACuaZ5oAAAAEUlEQVQoU2NgGAWjYBQMfQAAA8oAAZphnjsAAAAASUVORK5CYII="/><element name="sliderCapBottom" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAKCAYAAACuaZ5oAAAAEUlEQVQoU2NgGAWjYBQMfQAAA8oAAZphnjsAAAAASUVORK5CYII="/><element name="sliderRailCapTop" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAECAYAAACUY/8YAAAAX0lEQVR42q2P4QqAIAyEewktLUy3pKevVwvpAdZO+q9Qgw+OO25jQ88YM2blUAp4dW71epfvyuXcLCGsFWh4yD4fsHY6vV8kRpKUGFQND9kfHxQsJNqEOYOq4Wl2t/oPXdoiX8vd60IAAAAASUVORK5CYII="/><element name="sliderRailCapBottom" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAECAYAAACUY/8YAAAAXElEQVQY02NgIADExCQ+KSmp/FdWVkXBIDGg3BcGSoG0tMxGWVl5DAtAYiA5ii2wsbE1ALr0A8hAkKtBGMQGiYHkKLbg////TK6uboYg1wIN/QzCIDZIDCRHSD8AB2YrZ5n2CLAAAAAASUVORK5CYII="/><element name="sliderThumb" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAABCAAAAADhxTF3AAAAAnRSTlMA/1uRIrUAAAAUSURBVHjaY/oPA49unT+yaz2cCwAcKhapymVMMwAAAABJRU5ErkJggg=="/><element name="sliderThumbCapBottom" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAECAQAAAA+ajeTAAAAMElEQVQI12NgwACPPt76f/7/kf+7/q//yEAMeNQH19DHQBy41Xf+/ZH3u4hVjh8AAJAYGojU8tLHAAAAAElFTkSuQmCC"/><element name="sliderThumbCapTop" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAECAQAAAA+ajeTAAAANUlEQVQI12NgoAbY2rf+49KPs/uIVH54wrH/h/7v+L/y//QJRGm4/PHa/7NALdv+L/6MKQsAZV8ZczFGWjAAAAAASUVORK5CYII="/></elements></component><component name="tooltip"><settings><setting name="fontcase" value="normal"/><setting name="fontcolor" value="0xacacac"/><setting name="fontsize" value="14"/><setting name="fontweight" value="normal"/><setting name="activecolor" value="0xf01400"/><setting name="overcolor" value="0xffffff"/></settings><elements><element name="background" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAQAAAAECAYAAACp8Z5+AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDIxIDc5LjE1NTc3MiwgMjAxNC8wMS8xMy0xOTo0NDowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKFdpbmRvd3MpIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOjAxNTM3REZFMjkwODExRTVCRjRFOTA0Qjk4REU4RDI3IiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOjAxNTM3REZGMjkwODExRTVCRjRFOTA0Qjk4REU4RDI3Ij4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6MDE1MzdERkMyOTA4MTFFNUJGNEU5MDRCOThERThEMjciIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6MDE1MzdERkQyOTA4MTFFNUJGNEU5MDRCOThERThEMjciLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz5lfqfLAAAAGElEQVR42mIUkZR7xoAEmBjQAGEBgAADAE4gATkKpl/3AAAAAElFTkSuQmCC"/><element name="menuOption" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAQAAAAECAYAAACp8Z5+AAAADElEQVQImWNgoBwAAABEAAGC/mVLAAAAAElFTkSuQmCC"/><element name="menuOptionOver" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHgAAAAoCAYAAAA16j4lAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDIxIDc5LjE1NTc3MiwgMjAxNC8wMS8xMy0xOTo0NDowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKFdpbmRvd3MpIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOjNENjVEOTY1MjkwOTExRTVCNTE4RDUxMDBGREZCRDVGIiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOjNENjVEOTY2MjkwOTExRTVCNTE4RDUxMDBGREZCRDVGIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6M0Q2NUQ5NjMyOTA5MTFFNUI1MThENTEwMEZERkJENUYiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6M0Q2NUQ5NjQyOTA5MTFFNUI1MThENTEwMEZERkJENUYiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz7INKJRAAAAXklEQVR42uzRMREAMAgAsVJRnTogDenogMtL+MT7WUdruxYAFmABFmABFmABBizAAizAAizAAgxYgAVYgAVYgAVYgAELsAALsAALsAADFmABFmABFmABBizAmlkLMADhtQGC7uJgpgAAAABJRU5ErkJggg=="/><element name="volumeRail" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAB4CAYAAADlnULlAAAAYUlEQVRoge3NsREAEBREQRQlEihdINIUDUj+DNm+8ILbnILV1vdtX3PkyE+Jwq8Cg8FgMBgMBoPBYDAYDAaDwWAwGAwGg8FgMBgMBoPBYDAYDAaDwWAwGAwGg8FgMBj8qwO8VATwJ7CDgQAAAABJRU5ErkJggg=="/><element name="volumeProgress" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAB4CAYAAADlnULlAAAAYElEQVRoge3NoREAIAwEwYQKEPRfIYIOoAFMZsDtyRe/GcXWiH3b+4ys/LQq/CowGAwGg8FgMBgMBoPBYDAYDAaDwWAwGAwGg8FgMBgMBoPBYDAYDAaDwWAwGAwGg8G/OlO8A/A7bvlrAAAAAElFTkSuQmCC"/><element name="volumeThumb" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAA0AAAANCAYAAABy6+R8AAAA5UlEQVQokZ2SQWrCUBCG/39e7CnctNsKXkIw5YHdSE5gKeJ5KkjjDUqh0kWE9BASupX2FqYmMy7klWIXJn6rWXwfDMwQf3hZZbedKzch5Q7ANYCtmWb7nzodj+LP4DEMq3X+BHAqIsQJqmoGe74fDh5/o7fsY+6cTE/l/3E9Hw0HM76+r3tRJ9qIiJyPVKt91RcXuUmTAABE5OiTjJsEAZKxALhpEwHoCoBtq8TwLWaWtYpouZR1uVRVbeKrqu6qMpXE+8IMi0abGRaJ98XlHxE4Hto9mDEm0YXhC7R8V5Vp4n0RvAN372T8pAc16wAAAABJRU5ErkJggg=="/><!--element name="capTopLeft" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAcAAAAECAYAAABCxiV9AAAAFUlEQVQImWMUlpB9zoADMOGSoEwSAIdaATfZp+o0AAAAAElFTkSuQmCC"/><element name="capTopRight" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAcAAAAECAYAAABCxiV9AAAAFUlEQVQImWMUlpB9zoADMOGSoEwSAIdaATfZp+o0AAAAAElFTkSuQmCC"/><element name="capBottomLeft" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAcAAAAECAYAAABCxiV9AAAAFUlEQVQImWMUlpB9zoADMOGSoEwSAIdaATfZp+o0AAAAAElFTkSuQmCC"/><element name="capBottomRight" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAcAAAAECAYAAABCxiV9AAAAFUlEQVQImWMUlpB9zoADMOGSoEwSAIdaATfZp+o0AAAAAElFTkSuQmCC"/--><!--element name="capTop" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAECAYAAACHtL/sAAAAFklEQVQYlWMUlpB9zkABYKJE8+AwAAA0qQE3PjH/PwAAAABJRU5ErkJggg=="/><element name="capBottom" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAECAYAAACHtL/sAAAAFklEQVQYlWMUlpB9zkABYKJE8+AwAAA0qQE3PjH/PwAAAABJRU5ErkJggg=="/><element name="capLeft" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAECAYAAACHtL/sAAAAFklEQVQYlWMUlpB9zkABYKJE8+AwAAA0qQE3PjH/PwAAAABJRU5ErkJggg=="/><element name="capRight" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAECAYAAACHtL/sAAAAFklEQVQYlWMUlpB9zkABYKJE8+AwAAA0qQE3PjH/PwAAAABJRU5ErkJggg=="/--><element name="volumeCapTop" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAYAAAAGCAYAAADgzO9IAAAAFUlEQVQImWNkYGBgZMACmLAJ0ksCAASsAA0EItEAAAAAAElFTkSuQmCC"/><element name="volumeCapBottom" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAYAAAAGCAYAAADgzO9IAAAAFUlEQVQImWNkYGBgZMACmLAJ0ksCAASsAA0EItEAAAAAAElFTkSuQmCC"/><element name="volumeRailCapTop" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAYAAAAGCAYAAADgzO9IAAAAFUlEQVQImWNkYGBgZMACmLAJ0ksCAASsAA0EItEAAAAAAElFTkSuQmCC"/><element name="volumeRailCapBottom" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAYAAAAGCAYAAADgzO9IAAAAFUlEQVQImWNkYGBgZMACmLAJ0ksCAASsAA0EItEAAAAAAElFTkSuQmCC"/><element name="volumeProgressCapTop" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAYAAAAGCAYAAADgzO9IAAAAFUlEQVQImWNkYGBgZMACmLAJ0ksCAASsAA0EItEAAAAAAElFTkSuQmCC"/><element name="volumeProgressCapBottom" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAYAAAAGCAYAAADgzO9IAAAAFUlEQVQImWNkYGBgZMACmLAJ0ksCAASsAA0EItEAAAAAAElFTkSuQmCC"/></elements></component></components></skin>';
	var a;
	b.html5.defaultskin = function() {
		a = a || b.utils.parseXML(c);
		return a
	}
})(jwplayer);
(function(b) {
	var c = b.html5, g = b.utils, h = b.events, i = h.state, e = g.css, f = g
			.isMobile(), a = ".jwdisplay", d = ".jwpreview";
	var j = {
		showicons : true,
		bufferrotation : 45,
		bufferinterval : 100,
		fontcolor : "#ccc",
		overcolor : "#fff",
		fontsize : 15,
		fontweight : ""
	};
	c.display = function(n, O) {
		var A = n.skin, Y, ab, J, F, p, C, U, k = false, ac = {}, S = false, X = false, l, L, G, Q, H, Z = g
				.extend({}, j, A.getComponentSettings("display"), O), P = new h.eventdispatcher(), q, W;
		g.extend(this, P);
		function V() {
			Y = document.createElement("div");
			Y.id = n.id + "_display";
			Y.className = "jwdisplay";
			ab = document.createElement("div");
			ab.className = "jwpreview jw" + n.jwGetStretching();
			Y.appendChild(ab);
			n.jwAddEventListener(h.JWPLAYER_PLAYER_STATE, t);
			n.jwAddEventListener(h.JWPLAYER_PLAYLIST_ITEM, s);
			n.jwAddEventListener(h.JWPLAYER_PLAYLIST_COMPLETE, R);
			n.jwAddEventListener(h.JWPLAYER_MEDIA_ERROR, r);
			n.jwAddEventListener(h.JWPLAYER_ERROR, r);
			n.jwAddEventListener(h.JWPLAYER_PROVIDER_CLICK, aa);
			if (!f) {
				Y.addEventListener("click", aa, false)
			} else {
				J = new g.touch(Y);
				J.addEventListener(g.touchEvents.TAP, aa)
			}
			T();
			t({
				newstate : i.IDLE
			})
		}
		function aa(ae) {
			if (n.jwGetState() == i.IDLE && !X) {
				return
			}
			if (q && (n.jwGetControls() || n.jwGetState() === i.PLAYING)) {
				q(ae);
				return
			}
			if (!f || !n.jwGetControls()) {
				P.sendEvent(h.JWPLAYER_DISPLAY_CLICK)
			}
			if (!n.jwGetControls()) {
				return
			}
			var af = E();
			if (W && af - W < 500) {
				n.jwSetFullscreen();
				W = undefined
			} else {
				W = E()
			}
			var ad = g.bounds(Y.parentNode.querySelector(".jwcontrolbar")), ai = g
					.bounds(Y), ah = {
				left : ad.left - 10 - ai.left,
				right : ad.left + 30 - ai.left,
				top : ai.bottom - 40,
				bottom : ai.bottom
			}, ag = {
				left : ad.right - 30 - ai.left,
				right : ad.right + 10 - ai.left,
				top : ah.top,
				bottom : ah.bottom
			};
			if (f) {
				if (I(ah, ae.x, ae.y)) {
				} else {
					if (I(ag, ae.x, ae.y)) {
						n.jwSetFullscreen();
						return
					} else {
						P.sendEvent(h.JWPLAYER_DISPLAY_CLICK);
						if (l) {
							return
						}
					}
				}
			}
			switch (n.jwGetState()) {
			case i.PLAYING:
			case i.BUFFERING:
				n.jwPause();
				break;
			default:
				n.jwPlay();
				break
			}
		}
		function I(ae, ad, af) {
			return (ad >= ae.left && ad <= ae.right && af >= ae.top && af <= ae.bottom)
		}
		function E() {
			return new Date().getTime()
		}
		this.clickHandler = aa;
		function T() {
			var ad = {
				font : Z.fontweight + " " + Z.fontsize + "px/"
						+ (parseInt(Z.fontsize, 10) + 3)
						+ "px Arial, Helvetica, sans-serif",
				color : Z.fontcolor
			}, ae = {
				color : Z.overcolor
			};
			G = new c.displayicon(Y.id + "_button", n, ad, ae);
			Y.appendChild(G.element())
		}
		function v(ad, ae) {
			if (!Z.showicons) {
				return
			}
			if (ad || ae) {
				G.setRotation(ad === "buffer" ? parseInt(Z.bufferrotation, 10)
						: 0, parseInt(Z.bufferinterval, 10));
				G.setIcon(ad);
				G.setText(ae)
			} else {
				G.hide()
			}
		}
		function s() {
			w();
			F = n.jwGetPlaylist()[n.jwGetPlaylistIndex()];
			var ad = F ? F.image : "";
			H = undefined;
			o(ad)
		}
		function o(ad) {
			if (p !== ad) {
				if (p) {
					M(d, false)
				}
				p = ad;
				D()
			} else {
				if (p && !l) {
					M(d, true)
				}
			}
			u(n.jwGetState())
		}
		function R() {
			X = true;
			v("replay");
			var ad = n.jwGetPlaylist()[0];
			o(ad.image)
		}
		var B;
		function z() {
			return Q ? Q : (n ? n.jwGetState() : i.IDLE)
		}
		function t(ad) {
			clearTimeout(B);
			B = setTimeout(function() {
				u(ad.newstate)
			}, 100)
		}
		function u(ae) {
			ae = z();
			if (ae !== H) {
				H = ae;
				if (G) {
					G.setRotation(0)
				}
				switch (ae) {
				case i.IDLE:
					if (!S && !X) {
						if (p && !k) {
							M(d, true)
						}
						var ad = true;
						if (n._model && n._model.config.displaytitle === false) {
							ad = false
						}
						v("play", (F && ad) ? F.title : "")
					}
					break;
				case i.BUFFERING:
					w();
					X = false;
					v("buffer");
					break;
				case i.PLAYING:
					v();
					break;
				case i.PAUSED:
					v("play");
					break
				}
			}
		}
		this.forceState = function(ad) {
			Q = ad;
			u(ad);
			this.show()
		};
		this.releaseState = function(ad) {
			Q = null;
			u(ad);
			this.show()
		};
		this.hidePreview = function(ad) {
			k = ad;
			M(d, !ad);
			if (ad) {
				l = true
			}
		};
		this.setHiding = function() {
			l = true
		};
		this.element = function() {
			return Y
		};
		function N(ad) {
			return "#" + Y.id + " " + ad
		}
		function D() {
			if (p) {
				var ad = new Image();
				ad.addEventListener("load", y, false);
				ad.src = p
			} else {
				e(N(d), {
					"background-image" : ""
				});
				M(d, false);
				C = U = 0
			}
		}
		function y() {
			C = this.width;
			U = this.height;
			u(n.jwGetState());
			x();
			if (p) {
				e(N(d), {
					"background-image" : "url(" + p + ")"
				})
			}
		}
		function r(ad) {
			S = true;
			v("error", ad.message)
		}
		function w() {
			S = false;
			if (ac.error) {
				ac.error.setText()
			}
		}
		function x() {
			if (Y.clientWidth * Y.clientHeight > 0) {
				g.stretch(n.jwGetStretching(), ab, Y.clientWidth,
						Y.clientHeight, C, U)
			}
		}
		this.redraw = x;
		function M(ad, ae) {
			e(N(ad), {
				opacity : ae ? 1 : 0,
				visibility : ae ? "visible" : "hidden"
			})
		}
		this.show = function(ad) {
			if (G && (ad || z() !== i.PLAYING)) {
				K();
				Y.style.display = "block";
				G.show();
				l = false
			}
		};
		this.hide = function() {
			if (G) {
				G.hide();
				l = true
			}
		};
		function K() {
			clearTimeout(L);
			L = undefined
		}
		this.setAlternateClickHandler = function(ad) {
			q = ad
		};
		this.revertAlternateClickHandler = function() {
			q = null
		};
		V()
	};
	e(a, {
		position : "absolute",
		width : "100%",
		height : "100%",
		overflow : "hidden"
	});
	e(a + " " + d, {
		position : "absolute",
		width : "100%",
		height : "100%",
		background : "#000 no-repeat center",
		overflow : "hidden",
		opacity : 0
	});
	g.transitionStyle(a + ", " + a + " *", "opacity .25s, color .25s")
})(jwplayer);
(function(b) {
	var d = b.html5, h = b.utils, f = h.css, c = ".jwplayer .jwdisplayIcon", g = document, a = "none", e = "100%", i = "center";
	d.displayicon = function(o, I, t, C) {
		var w = I.skin, Q = I.id, x, J, P, A, v, n, D, B = {}, F, E = 0, y = -1, M = 0;
		if (I instanceof b.html5.instream) {
			Q = Q.replace("_instream", "")
		}
		function z() {
			x = L("jwdisplayIcon");
			x.id = o;
			u();
			D = L("jwicon", x);
			n = L("jwtext", x, t, C);
			I.jwAddEventListener(b.events.JWPLAYER_RESIZE, s);
			k();
			l()
		}
		function q() {
			return "#" + o
		}
		function L(S, U, T, R) {
			var V = g.createElement("div");
			V.className = S;
			if (U) {
				U.appendChild(V)
			}
			if (x) {
				N(S, "." + S, T, R)
			}
			return V
		}
		function u() {
			J = G("background");
			P = G("capLeft");
			A = G("capRight");
			v = (P.width * A.width > 0);
			var R = {
				"background-image" : "url(" + P.src + "), url(" + J.src
						+ "), url(" + A.src + ")",
				"background-position" : "left,center,right",
				"background-repeat" : "no-repeat",
				padding : "0 " + A.width + "px 0 " + P.width + "px",
				height : J.height,
				"margin-top" : J.height / -2
			};
			f(q(), R);
			if (!h.isMobile()) {
				if (J.overSrc) {
					R["background-image"] = "url(" + P.overSrc + "), url("
							+ J.overSrc + "), url(" + A.overSrc + ")"
				}
				f(".jw-tab-focus " + q() + ", #" + Q + " .jwdisplay:hover "
						+ q(), R)
			}
		}
		function N(T, R, V, S) {
			var U = G(T);
			if (T === "replayIcon" && !U.src) {
				U = G("playIcon")
			}
			if (U.overSrc) {
				S = h.extend({}, S);
				S["background-image"] = "url(" + U.overSrc + ")"
			}
			if (U.src) {
				V = h.extend({}, V);
				if (T.indexOf("Icon") > 0) {
					E = U.width | 0
				}
				V.width = U.width;
				V["background-image"] = "url(" + U.src + ")";
				V["background-size"] = U.width + "px " + U.height + "px";
				V["float"] = "none";
				f.style(x, {
					display : "table"
				})
			} else {
				f.style(x, {
					display : "none"
				})
			}
			if (V) {
				f("#" + Q + " .jwdisplay " + R, V)
			}
			if (S) {
				f("#" + Q + " .jwdisplay:hover " + R, S)
			}
			F = U
		}
		function G(S) {
			var T = w.getSkinElement("display", S), R = w.getSkinElement(
					"display", S + "Over");
			if (T) {
				T.overSrc = (R && R.src) ? R.src : "";
				return T
			}
			return {
				src : "",
				overSrc : "",
				width : 0,
				height : 0
			}
		}
		function l() {
			var R = v || (E === 0);
			f.style(n, {});
			M = R ? 30 : 0;
			s()
		}
		function s() {
			clearTimeout(y);
			if (M-- > 0) {
				y = setTimeout(s, 33)
			}
			var T = "px " + e;
			var R = Math.ceil(Math.max(F.width, h.bounds(x).width - A.width
					- P.width));
			var S = [ P.width + T, R + T, A.width + T ].join(", ");
			var U = {
				"background-size" : S
			};
			if (x.parentNode) {
				U.left = (x.parentNode.clientWidth % 2 === 1) ? "0.5px" : ""
			}
			f.style(x, U)
		}
		this.element = function() {
			return x
		};
		this.setText = function(S) {
			var R = n.style;
			R.height = "0";
			if (S) {
				n.innerHTML = ""
			}
			l()
		};
		this.setIcon = function(R) {
			var S = B[R];
			if (!S) {
				S = L("jwicon");
				S.id = x.id + "_" + R;
				B[R] = S
			}
			N(R + "Icon", "#" + S.id);
			if (x.contains(D)) {
				x.replaceChild(S, D)
			} else {
				x.appendChild(S)
			}
			D = S
		};
		var r, p = 0, O;
		function H(S, R) {
			clearInterval(r);
			O = 0;
			p = S | 0;
			if (p === 0) {
				K()
			} else {
				r = setInterval(K, R)
			}
		}
		function K() {
			O = (O + p) % 360;
			h.rotate(D, O)
		}
		this.setRotation = H;
		function j(R) {
			return Math.floor(R.scrollHeight
					/ g.defaultView.getComputedStyle(R, null).lineHeight
							.replace("px", ""))
		}
		var k = this.hide = function() {
			x.style.opacity = 0;
			x.style.cursor = ""
		};
		this.show = function() {
			x.style.opacity = 1;
			x.style.cursor = "pointer"
		};
		z()
	};
	f(c, {
		display : "table",
		position : "relative",
		"margin-left" : "auto",
		"margin-right" : "auto",
		top : "50%",
		"float" : "none"
	});
	f(c + " div", {
		position : "relative",
		display : "table-cell",
		"vertical-align" : "middle",
		"background-repeat" : "no-repeat",
		"background-position" : i
	});
	f(c + " div", {
		"vertical-align" : "middle"
	}, true);
	f(c + " .jwtext", {
		color : "#fff",
		padding : "0 1px",
		"max-width" : "300px",
		"overflow-y" : "hidden",
		"text-align" : i,
		"-webkit-user-select" : a,
		"-moz-user-select" : a,
		"-ms-user-select" : a,
		"user-select" : a
	})
})(jwplayer);
(function(c) {
	var b = c.html5, a = c.utils, d = a.css, h = a.bounds, g = (window.top !== window.self), e = ".jwdock", f = ".jwdockbuttons";
	b.dock = function(u, F) {
		var x = u, w = {
			iconalpha : 0.75,
			iconalphaactive : 0.5,
			iconalphaover : 1,
			margin : 8
		}, p = a.extend({}, w, F), i = x.id + "_dock", q = x.skin, A = 0, l = {}, n = {}, r, B, G, y, z = this;
		function s() {
			z.visible = false;
			r = E("div", "jwdock");
			B = E("div", "jwdockbuttons");
			r.appendChild(B);
			r.id = i;
			t();
			setTimeout(function() {
				G = h(r)
			})
		}
		function t() {
			var I = v("button"), J = v("buttonOver"), K = v("buttonActive");
			if (!I) {
				return
			}
			d(j(), {
				height : I.height,
				padding : p.margin
			});
			d(f, {
				height : I.height
			});
			d(j("div.button"), a.extend(o(I), {
				width : I.width,
				cursor : "pointer",
				border : "none"
			}));
			d(j("div.button:hover"), o(J));
			d(j("div.button:active"), o(K));
			d(j("div.button>div"), {
				opacity : p.iconalpha
			});
			d(j("div.button:hover>div"), {
				opacity : p.iconalphaover
			});
			d(j("div.button:active>div"), {
				opacity : p.iconalphaactive
			});
			d(j(".jwoverlay"), {
				top : p.margin + I.height
			});
			C("capLeft", B);
			C("capRight", B);
			C("divider")
		}
		function o(I) {
			if (!(I && I.src)) {
				return {}
			}
			return {
				background : "url(" + I.src + ") center",
				"background-size" : I.width + "px " + I.height + "px"
			}
		}
		function C(K, J) {
			var I = v(K);
			d(j("." + K), a.extend(o(I), {
				width : I.width
			}));
			return E("div", K, J)
		}
		function j(I) {
			return "#" + i + " " + (I ? I : "")
		}
		function E(K, I, J) {
			var L = document.createElement(K);
			if (I) {
				L.className = I
			}
			if (J) {
				J.appendChild(L)
			}
			return L
		}
		function v(I) {
			var J = q.getSkinElement("dock", I);
			return J ? J : {
				width : 0,
				height : 0,
				src : ""
			}
		}
		z.redraw = function() {
			G = h(r)
		};
		function D() {
			return (g && a.isIE() && x.jwGetFullscreen())
		}
		function H(J) {
			var M = n[J], I, L = l[J], N, K = h(L.icon);
			M.offsetX(0);
			N = h(r);
			if (D()) {
				d("#" + M.element().id, {
					left : K.left * 100 + 50 + K.width * 100 / 2
				})
			} else {
				d("#" + M.element().id, {
					left : K.left - N.left + K.width / 2
				})
			}
			I = h(M.element());
			if (N.left > I.left) {
				M.offsetX(N.left - I.left + 8)
			}
		}
		z.element = function() {
			return r
		};
		z.offset = function(I) {
			d(j(), {
				"margin-left" : I
			})
		};
		z.hide = function() {
			if (!z.visible) {
				return
			}
			z.visible = false;
			r.style.opacity = 0;
			clearTimeout(y);
			y = setTimeout(function() {
				r.style.display = "none"
			}, 250)
		};
		z.showTemp = function() {
			if (!z.visible) {
				r.style.opacity = 0;
				r.style.display = "block"
			}
		};
		z.hideTemp = function() {
			if (!z.visible) {
				r.style.display = "none"
			}
		};
		z.show = function() {
			if (z.visible || !A) {
				return
			}
			z.visible = true;
			r.style.display = "block";
			clearTimeout(y);
			y = setTimeout(function() {
				r.style.opacity = 1
			}, 0)
		};
		z.addButton = function(J, R, N, K) {
			if (l[K]) {
				return
			}
			var L = E("div", "divider", B), M = E("div", "button", B), Q = E(
					"div", null, M);
			Q.id = i + "_" + K;
			Q.innerHTML = "&nbsp;";
			d("#" + Q.id, {
				"background-image" : J
			});
			if (typeof N === "string") {
				N = new Function(N)
			}
			if (!a.isMobile()) {
				M.addEventListener("click", function(T) {
					N(T);
					T.preventDefault()
				})
			} else {
				var I = new a.touch(M);
				I.addEventListener(a.touchEvents.TAP, function(T) {
					N(T)
				})
			}
			l[K] = {
				element : M,
				label : R,
				divider : L,
				icon : Q
			};
			if (R) {
				var S = new b.overlay(Q.id + "_tooltip", q, true), O = E("div");
				O.id = Q.id + "_label";
				O.innerHTML = R;
				d("#" + O.id, {
					padding : 3
				});
				S.setContents(O);
				if (!a.isMobile()) {
					var P;
					M.addEventListener("mouseover", function() {
						clearTimeout(P);
						H(K);
						S.show();
						a.foreach(n, function(T, U) {
							if (T !== K) {
								U.hide()
							}
						})
					}, false);
					M.addEventListener("mouseout", function() {
						P = setTimeout(S.hide, 100)
					}, false);
					r.appendChild(S.element());
					n[K] = S
				}
			}
			A++;
			k()
		};
		z.removeButton = function(J) {
			if (l[J]) {
				B.removeChild(l[J].element);
				B.removeChild(l[J].divider);
				var I = document.getElementById("" + i + "_" + J + "_tooltip");
				if (I) {
					r.removeChild(I)
				}
				delete l[J];
				A--;
				k()
			}
		};
		z.numButtons = function() {
			return A
		};
		function k() {
			d(f + " .capLeft, " + f + " .capRight", {
				display : A ? "block" : "none"
			})
		}
		s()
	};
	d(e, {
		opacity : 0,
		display : "none"
	});
	d(e + " > *", {
		height : "100%",
		"float" : "left"
	});
	d(e + " > .jwoverlay", {
		height : "auto",
		"float" : "none",
		"z-index" : 99
	});
	d(f + " div.button", {
		position : "relative"
	});
	d(f + " > *", {
		height : "100%",
		"float" : "left"
	});
	d(f + " .divider", {
		display : "none"
	});
	d(f + " div.button ~ .divider", {
		display : "block"
	});
	d(f + " .capLeft, " + f + " .capRight", {
		display : "none"
	});
	d(f + " .capRight", {
		"float" : "right"
	});
	d(f + " div.button > div", {
		left : 0,
		right : 0,
		top : 0,
		bottom : 0,
		margin : 5,
		position : "absolute",
		"background-position" : "center",
		"background-repeat" : "no-repeat"
	});
	a.transitionStyle(e, "background .25s, opacity .25s");
	a.transitionStyle(e + " .jwoverlay", "opacity .25s");
	a.transitionStyle(f + " div.button div", "opacity .25s")
})(jwplayer);
(function(c) {
	var a = c.html5, e = c.utils, b = c._, f = c.events, d = f.state, g = c.playlist;
	a.instream = function(D, H, o, y) {
		var v = {
			controlbarseekable : "never",
			controlbarpausable : true,
			controlbarstoppable : true,
			loadingmessage : "Loading ad",
			playlistclickable : true,
			skipoffset : null,
			tag : null
		};
		var x, N, z = 0, K, A = {
			controlbarseekable : "never",
			controlbarpausable : false,
			controlbarstoppable : false
		}, h, u, J, j, k, L, l, q, p, i, G = -1, F = e.extend(this,
				new f.eventdispatcher());
		D.jwAddEventListener(f.JWPLAYER_RESIZE, s);
		D.jwAddEventListener(f.JWPLAYER_FULLSCREEN, r);
		F.init = function() {
			u = y.detachMedia();
			I();
			L = new a.model({}, l);
			L.setVolume(H.volume);
			L.setFullscreen(H.fullscreen);
			L.setMute(H.mute);
			L.addEventListener("fullscreenchange", B);
			k = H.playlist[H.item];
			J = u.currentTime;
			if (y.checkBeforePlay()
					|| (J === 0 && !H.getVideo().checkComplete())) {
				J = 0;
				j = d.PLAYING
			} else {
				if (H.getVideo().checkComplete()) {
					j = d.IDLE
				} else {
					if (D.jwGetState() === d.IDLE) {
						j = d.IDLE
					} else {
						j = d.PLAYING
					}
				}
			}
			if (j === d.PLAYING) {
				u.pause()
			}
			p = new a.display(F);
			p.forceState(d.BUFFERING);
			i = document.createElement("div");
			i.id = F.id + "_instream_container";
			e.css.style(i, {
				width : "100%",
				height : "100%"
			});
			i.appendChild(p.element());
			var Q = {
				fullscreen : H.fullscreen
			};
			q = new a.controlbar(F, Q);
			q.instreamMode(true);
			i.appendChild(q.element());
			if (D.jwGetControls()) {
				q.show();
				p.show()
			} else {
				q.hide();
				p.hide()
			}
			o.setupInstream(i, q, p, L);
			s();
			F.jwInstreamSetText(v.loadingmessage)
		};
		F.load = function(U, R) {
			if (e.isAndroid(2.3)) {
				t({
					type : f.JWPLAYER_ERROR,
					message : "Error loading instream: Cannot play instream on Android 2.3"
				});
				return
			}
			E(f.JWPLAYER_PLAYLIST_ITEM, {
				index : z
			}, true);
			var T = i.parentNode;
			var Q = 10 + e.bounds(T).bottom - e.bounds(q.element()).top;
			if (b.isArray(U)) {
				if (R) {
					K = R;
					R = R[z]
				}
				N = U;
				U = N[z]
			}
			A = e.extend(v, R);
			x = new g.item(U);
			L.setPlaylist([ U ]);
			h = new a.adskipbutton(D.id, Q, A.skipMessage, A.skipText);
			h.addEventListener(f.JWPLAYER_AD_SKIPPED, n);
			h.reset(A.skipoffset || -1);
			if (D.jwGetControls()) {
				h.show()
			} else {
				h.hide()
			}
			var S = h.element();
			i.appendChild(S);
			L.addEventListener(f.JWPLAYER_ERROR, t);
			p.setAlternateClickHandler(function(V) {
				V = V || {};
				V.hasControls = !!D.jwGetControls();
				E(f.JWPLAYER_INSTREAM_CLICK, V);
				if (L.state === d.PAUSED) {
					if (V.hasControls) {
						F.jwInstreamPlay()
					}
				} else {
					F.jwInstreamPause()
				}
			});
			if (e.isMSIE()) {
				u.parentElement.addEventListener("click", p.clickHandler)
			}
			o.addEventListener(f.JWPLAYER_AD_SKIPPED, n);
			l.load(L.playlist[0])
		};
		function t(Q) {
			E(Q.type, Q);
			if (L) {
				D.jwInstreamDestroy(false, F)
			}
		}
		F.jwInstreamDestroy = function(Q) {
			if (!L) {
				return
			}
			L.removeEventListener("fullscreenchange", B);
			clearTimeout(G);
			G = -1;
			l.detachMedia();
			y.attachMedia();
			if (j !== d.IDLE) {
				var R = e.extend({}, k);
				R.starttime = J;
				H.getVideo().load(R)
			} else {
				H.getVideo().stop()
			}
			F.resetEventListeners();
			l.resetEventListeners();
			L.resetEventListeners();
			if (q) {
				try {
					q.element().parentNode.removeChild(q.element())
				} catch (S) {
				}
			}
			if (p) {
				if (u && u.parentElement) {
					u.parentElement
							.removeEventListener("click", p.clickHandler)
				}
				p.revertAlternateClickHandler()
			}
			E(f.JWPLAYER_INSTREAM_DESTROYED, {
				reason : Q ? "complete" : "destroyed"
			}, true);
			if (j === d.PLAYING) {
				u.play()
			}
			o.destroyInstream(l.isAudioFile());
			L = null
		};
		F.jwInstreamAddEventListener = function(Q, R) {
			F.addEventListener(Q, R)
		};
		F.jwInstreamRemoveEventListener = function(Q, R) {
			F.removeEventListener(Q, R)
		};
		F.jwInstreamPlay = function() {
			l.play(true);
			H.state = d.PLAYING;
			p.show()
		};
		F.jwInstreamPause = function() {
			l.pause(true);
			H.state = d.PAUSED;
			if (D.jwGetControls()) {
				p.show();
				q.show()
			}
		};
		F.jwInstreamSeek = function(Q) {
			l.seek(Q)
		};
		F.jwInstreamSetText = function(Q) {
			q.setText(Q)
		};
		F.jwInstreamState = function() {
			return L.state
		};
		function I() {
			var Q = c.html5.chooseProvider({});
			l = new Q(H.id);
			l.addGlobalListener(M);
			l.addEventListener(f.JWPLAYER_MEDIA_META, P);
			l.addEventListener(f.JWPLAYER_MEDIA_COMPLETE, w);
			l.addEventListener(f.JWPLAYER_MEDIA_BUFFER_FULL, C);
			l.addEventListener(f.JWPLAYER_MEDIA_ERROR, t);
			l.addEventListener(f.JWPLAYER_PLAYER_STATE, O);
			l.addEventListener(f.JWPLAYER_MEDIA_TIME, function(R) {
				if (h) {
					h.updateSkipTime(R.position, R.duration)
				}
			});
			l.attachMedia();
			l.mute(H.mute);
			l.volume(H.volume)
		}
		function O(Q) {
			if (Q.newstate === L.state) {
				return
			}
			L.state = Q.newstate;
			switch (L.state) {
			case d.PLAYING:
				F.jwInstreamPlay();
				break;
			case d.PAUSED:
				F.jwInstreamPause();
				break
			}
		}
		function n(Q) {
			E(Q.type, Q);
			w()
		}
		function M(Q) {
			E(Q.type, Q)
		}
		function B(Q) {
			H.sendEvent(Q.type, Q);
			E(f.JWPLAYER_FULLSCREEN, {
				fullscreen : Q.jwstate
			})
		}
		function r(Q) {
			M(Q);
			if (!L) {
				return
			}
			s();
			if (!Q.fullscreen && e.isIPad()) {
				if (L.state === d.PAUSED) {
					p.show(true)
				} else {
					if (L.state === d.PLAYING) {
						p.hide()
					}
				}
			}
		}
		function C() {
			if (p) {
				p.releaseState(F.jwGetState())
			}
			l.play()
		}
		function w() {
			if (N && z + 1 < N.length) {
				z++;
				var Q = N[z];
				x = new g.item(Q);
				L.setPlaylist([ Q ]);
				var R;
				if (K) {
					R = K[z]
				}
				A = e.extend(v, R);
				l.load(L.playlist[0]);
				h.reset(A.skipoffset || -1);
				G = setTimeout(function() {
					E(f.JWPLAYER_PLAYLIST_ITEM, {
						index : z
					}, true)
				}, 0)
			} else {
				G = setTimeout(function() {
					E(f.JWPLAYER_PLAYLIST_COMPLETE, {}, true);
					D.jwInstreamDestroy(true, F)
				}, 0)
			}
		}
		function P(Q) {
			if (Q.width && Q.height) {
				if (p) {
					p.releaseState(F.jwGetState())
				}
				o.resizeMedia()
			}
		}
		function E(Q, R) {
			R = R || {};
			if (v.tag && !R.tag) {
				R.tag = v.tag
			}
			F.sendEvent(Q, R)
		}
		function s() {
			if (q) {
				q.redraw()
			}
			if (p) {
				p.redraw()
			}
		}
		F.setControls = function(Q) {
			if (Q) {
				h.show()
			} else {
				h.hide()
			}
		};
		F.jwPlay = function() {
			if (A.controlbarpausable.toString().toLowerCase() === "true") {
				F.jwInstreamPlay()
			}
		};
		F.jwPause = function() {
			if (A.controlbarpausable.toString().toLowerCase() === "true") {
				F.jwInstreamPause()
			}
		};
		F.jwStop = function() {
			if (A.controlbarstoppable.toString().toLowerCase() === "true") {
				D.jwInstreamDestroy(false, F);
				D.jwStop()
			}
		};
		F.jwSeek = function(Q) {
			switch (A.controlbarseekable.toLowerCase()) {
			case "never":
				return;
			case "always":
				F.jwInstreamSeek(Q);
				break;
			case "backwards":
				if (L.position > Q) {
					F.jwInstreamSeek(Q)
				}
				break
			}
		};
		F.jwSeekDrag = function(Q) {
			L.seekDrag(Q)
		};
		F.jwGetPosition = function() {
		};
		F.jwGetDuration = function() {
		};
		F.jwGetWidth = D.jwGetWidth;
		F.jwGetHeight = D.jwGetHeight;
		F.jwGetFullscreen = D.jwGetFullscreen;
		F.jwSetFullscreen = D.jwSetFullscreen;
		F.jwGetVolume = function() {
			return H.volume
		};
		F.jwSetVolume = function(Q) {
			L.setVolume(Q);
			D.jwSetVolume(Q)
		};
		F.jwGetMute = function() {
			return H.mute
		};
		F.jwSetMute = function(Q) {
			L.setMute(Q);
			D.jwSetMute(Q)
		};
		F.jwGetState = function() {
			if (!L) {
				return d.IDLE
			}
			return L.state
		};
		F.jwGetPlaylist = function() {
			return [ x ]
		};
		F.jwGetPlaylistIndex = function() {
			return 0
		};
		F.jwGetStretching = function() {
			return H.config.stretching
		};
		F.jwAddEventListener = function(R, Q) {
			F.addEventListener(R, Q)
		};
		F.jwRemoveEventListener = function(R, Q) {
			F.removeEventListener(R, Q)
		};
		F.jwSetCurrentQuality = function() {
		};
		F.jwGetQualityLevels = function() {
			return []
		};
		F.jwGetControls = function() {
			return D.jwGetControls()
		};
		F.skin = D.skin;
		F.id = D.id + "_instream";
		return F
	}
})(window.jwplayer);
(function(b) {
	var i = b.utils, e = b.html5, h = i.css, k = b.events.state, f = "free", c = "pro", d = "premium", j = "ads", l = "", g = ".jwlogo";
	var a = e.logo = function(u, v) {
		var A = u, B = A.id + "_logo", r, o, s = a.defaults, z = false;
		function t() {
			y();
			p()
		}
		function y() {
			var C = "o";
			if (A.edition) {
				C = w(A.edition())
			}
			if (C === "o" || C === "f") {
				s.link = l + b.version + "&m=h&e=" + C
			}
			r = i.extend({}, s, v);
			r.hide = (r.hide.toString() === "true")
		}
		function p() {
			o = document.createElement("img");
			o.className = "jwlogo";
			o.id = B;
			if (!r.file) {
				o.style.display = "none";
				return
			}
			var C = (/(\w+)-(\w+)/).exec(r.position), D = {}, F = r.margin;
			if (C.length === 3) {
				D[C[1]] = F;
				D[C[2]] = F
			} else {
				D.top = D.right = F
			}
			h(n(), D);
			o.src = (r.prefix ? r.prefix : "") + r.file;
			if (!i.isMobile()) {
				o.onclick = x
			} else {
				var E = new i.touch(o);
				E.addEventListener(i.touchEvents.TAP, x)
			}
		}
		this.resize = function() {
		};
		this.element = function() {
			return o
		};
		this.offset = function(C) {
			h(n(), {
				"margin-bottom" : C
			})
		};
		this.position = function() {
			return r.position
		};
		this.margin = function() {
			return parseInt(r.margin, 10)
		};
		function q() {
			if (A.jwGetState() === k.IDLE || A.jwGetState() === k.PAUSED) {
				A.jwPlay()
			} else {
				A.jwPause()
			}
		}
		function x(C) {
			if (i.exists(C) && C.stopPropagation) {
				C.stopPropagation()
			}
			if (!z || !r.link) {
				q()
			}
			if (z && r.link) {
				A.jwPause();
				A.jwSetFullscreen(false);
				window.open(r.link, r.linktarget)
			}
			return
		}
		function w(C) {
			if (C === c) {
				return "p"
			} else {
				if (C === d) {
					return "r"
				} else {
					if (C === j) {
						return "a"
					} else {
						if (C === f) {
							return "f"
						} else {
							return "o"
						}
					}
				}
			}
		}
		function n(C) {
			return "#" + B + " " + (C ? C : "")
		}
		this.hide = function(C) {
			if (r.hide || C) {
				z = false;
				o.style.visibility = "hidden";
				o.style.opacity = 0
			}
		};
		this.show = function() {
			z = true;
			o.style.visibility = "visible";
			o.style.opacity = 1
		};
		t();
		return this
	};
	a.defaults = {
		prefix : i.repo(),
		file : null,
		linktarget : "_top",
		margin : 8,
		hide : false,
		position : "top-right"
	};
	h(g, {
		cursor : "pointer",
		position : "absolute"
	})
})(jwplayer);
(function(e) {
	var d = e.html5, b = e.utils, f = b.css, g = "jwmenu", a = "jwoption";
	d.menu = function(i, j, x, q) {
		var u = j, k = q, n = new d.overlay(u + "_overlay", x), o = b.extend({
			fontcase : undefined,
			fontcolor : "#cccccc",
			fontsize : 12,
			fontweight : undefined,
			activecolor : "#ffffff",
			overcolor : "#ffffff"
		}, x.getComponentSettings("tooltip")), l, w = [];
		function t() {
			l = r(g);
			l.id = u;
			var C = p("menuTop" + i), A = p("menuOption"), z = p("menuOptionOver"), B = p("menuOptionActive");
			if (C && C.image) {
				var D = new Image();
				D.src = C.src;
				D.width = C.width;
				D.height = C.height;
				l.appendChild(D)
			}
			if (A) {
				var y = "#" + j + " ." + a;
				f(y, b.extend(v(A), {
					height : "20px",
					color : o.fontcolor,
					"padding-left" : "46px",
					"padding-right" : "46px",
					"padding-top" : "20px",
					font : o.fontweight + " " + o.fontsize
							+ "px Arial,Helvetica,sans-serif",
					"line-height" : A.height,
					"text-transform" : (o.fontcase === "upper") ? "uppercase"
							: undefined
				}));
				f(y + ":hover", b.extend(v(z), {
					color : o.overcolor
				}));
				f(y + ".active", b.extend(v(B), {
					color : o.activecolor
				}))
			}
			n.setContents(l)
		}
		function v(y) {
			if (!(y && y.src)) {
				return {}
			}
			return {
				background : "url(" + y.src + ") no-repeat left",
				"background-size" : y.width + "px " + y.height + "px"
			}
		}
		this.element = function() {
			return n.element()
		};
		this.addOption = function(z, B) {
			var A = r(a, l);
			A.id = u + "_option_" + B;
			A.innerHTML = z;
			if (!b.isMobile()) {
				A.addEventListener("click", s(w.length, B))
			} else {
				var y = new b.touch(A);
				y.addEventListener(b.touchEvents.TAP, s(w.length, B))
			}
			w.push(A)
		};
		function s(y, z) {
			return function() {
				h(y);
				if (k) {
					k(z)
				}
			}
		}
		this.clearOptions = function() {
			while (w.length > 0) {
				l.removeChild(w.pop())
			}
		};
		var h = this.setActive = function(y) {
			for (var z = 0; z < w.length; z++) {
				var A = w[z];
				A.className = A.className.replace(" active", "");
				if (z === y) {
					A.className += " active"
				}
			}
		};
		function r(z, y) {
			var A = document.createElement("div");
			if (z) {
				A.className = z
			}
			if (y) {
				y.appendChild(A)
			}
			return A
		}
		function p(y) {
			var z = x.getSkinElement("tooltip", y);
			return z ? z : {
				width : 0,
				height : 0,
				src : undefined
			}
		}
		this.show = n.show;
		this.hide = n.hide;
		this.offsetX = n.offsetX;
		this.positionX = n.positionX;
		this.constrainX = n.constrainX;
		this.positionY = n.positionY;
		t()
	};
	function c(h) {
		return "." + h.replace(/ /g, " .")
	}
	f(c(g + " " + a), {
		cursor : "pointer",
		"white-space" : "nowrap",
		position : "relative"
	})
})(jwplayer);
(function(c) {
	var b = c.html5, a = c.utils, d = c.events;
	b.model = function(h, e) {
		var o = this, j, q = a.getCookies(), f = {
			controlbar : {},
			display : {}
		}, g = a.noop, l = {
			autostart : false,
			controls : true,
			fullscreen : false,
			height : 320,
			mobilecontrols : false,
			mute : false,
			playlist : [],
			playlistposition : "none",
			playlistsize : 180,
			playlistlayout : "extended",
			repeat : false,
			stretching : a.stretching.UNIFORM,
			width : 480,
			volume : 90
		};
		function n(r) {
			a.foreach(r, function(s, t) {
				r[s] = a.serialize(t)
			});
			return r
		}
		function p() {
			a.extend(o, new d.eventdispatcher());
			o.config = n(a.extend({}, l, q, h));
			a.extend(o, {
				id : h.id,
				state : d.state.IDLE,
				duration : -1,
				position : 0,
				buffer : 0
			}, o.config);
			o.playlist = [];
			o.setItem(0)
		}
		var k = {};
		k[d.JWPLAYER_MEDIA_MUTE] = [ "mute" ];
		k[d.JWPLAYER_MEDIA_VOLUME] = [ "volume" ];
		k[d.JWPLAYER_PLAYER_STATE] = [ "newstate->state" ];
		k[d.JWPLAYER_MEDIA_BUFFER] = [ "bufferPercent->buffer" ];
		k[d.JWPLAYER_MEDIA_TIME] = [ "position", "duration" ];
		function i(r) {
			var y = k[r.type];
			if (y && y.length) {
				var x = false;
				for (var v = 0; v < y.length; v++) {
					var t = y[v];
					var u = t.split("->");
					var w = u[0];
					var s = u[1] || w;
					if (o[s] !== r[w]) {
						o[s] = r[w];
						x = true
					}
				}
				if (x) {
					o.sendEvent(r.type, r)
				}
			} else {
				o.sendEvent(r.type, r)
			}
		}
		o.setVideoProvider = function(s) {
			if (j) {
				j.removeGlobalListener(i);
				var r = j.getContainer();
				if (r) {
					j.remove();
					s.setContainer(r)
				}
			}
			j = s;
			j.volume(o.volume);
			j.mute(o.mute);
			j.addGlobalListener(i)
		};
		o.destroy = function() {
			if (j) {
				j.removeGlobalListener(i);
				j.destroy()
			}
		};
		o.getVideo = function() {
			return j
		};
		o.seekDrag = function(r) {
			j.seekDrag(r)
		};
		o.setFullscreen = function(r) {
			r = !!r;
			if (r !== o.fullscreen) {
				o.fullscreen = r;
				o.sendEvent(d.JWPLAYER_FULLSCREEN, {
					fullscreen : r
				})
			}
		};
		o.setPlaylist = function(r) {
			o.playlist = c.playlist.filterPlaylist(r, o.androidhls);
			if (o.playlist.length === 0) {
				o
						.sendEvent(
								d.JWPLAYER_ERROR,
								{
									message : "Error loading playlist: No playable sources found"
								})
			} else {
				o.sendEvent(d.JWPLAYER_PLAYLIST_LOADED, {
					playlist : c(o.id).getPlaylist()
				});
				o.item = -1;
				o.setItem(0)
			}
		};
		o.setItem = function(r) {
			var s;
			var v = false;
			if (r === o.playlist.length || r < -1) {
				s = 0;
				v = true
			} else {
				if (r === -1 || r > o.playlist.length) {
					s = o.playlist.length - 1
				} else {
					s = r
				}
			}
			if (v || s !== o.item) {
				o.item = s;
				o.sendEvent(d.JWPLAYER_PLAYLIST_ITEM, {
					index : o.item
				});
				var t = o.playlist[s];
				var u = t && t.sources && t.sources[0];
				var w = b.chooseProvider(u);
				if (!(g instanceof w)) {
					g = e || new w(o.id);
					o.setVideoProvider(g)
				}
				if (g.init) {
					g.init(t)
				}
			}
		};
		o.setVolume = function(r) {
			if (o.mute && r > 0) {
				o.setMute(false)
			}
			r = Math.round(r);
			if (!o.mute) {
				a.saveCookie("volume", r)
			}
			i({
				type : d.JWPLAYER_MEDIA_VOLUME,
				volume : r
			});
			j.volume(r)
		};
		o.setMute = function(r) {
			if (!a.exists(r)) {
				r = !o.mute
			}
			a.saveCookie("mute", r);
			i({
				type : d.JWPLAYER_MEDIA_MUTE,
				mute : r
			});
			j.mute(r)
		};
		o.componentConfig = function(r) {
			return f[r]
		};
		p()
	}
})(jwplayer);
(function(e) {
	var h = e.html5, l = e.utils, k = l.css, n = l.transitionStyle, b = ".jwoverlay", g = "jwcontents", d = "top", j = "bottom", i = "right", a = "left", c = "#ffffff", f = {
		fontcase : undefined,
		fontcolor : c,
		fontsize : 12,
		fontweight : undefined,
		activecolor : c,
		overcolor : c
	};
	h.overlay = function(A, C, y) {
		var D = this, q = A, v = C, E = y, w, H, B, o, p = l.extend({}, f, v
				.getComponentSettings("tooltip")), s = {};
		function x() {
			w = F(b.replace(".", ""));
			w.id = q;
			var J = r("arrow", "jwarrow");
			o = J[0];
			B = J[1];
			k.style(o, {
				position : "absolute",
				bottom : E ? undefined : 0,
				top : E ? 0 : undefined,
				width : B.width,
				height : B.height,
				left : "50%"
			});
			G(d, a);
			G(j, a);
			G(d, i);
			G(j, i);
			G(a);
			G(i);
			G(d);
			G(j);
			var I = r("background", "jwback");
			k.style(I[0], {
				left : s.left,
				right : s.right,
				top : s.top,
				bottom : s.bottom
			});
			H = F(g, w);
			k(t(g) + " *", {
				color : p.fontcolor,
				font : p.fontweight + " " + (p.fontsize)
						+ "px Arial,Helvetica,sans-serif",
				"text-transform" : (p.fontcase === "upper") ? "uppercase"
						: undefined
			});
			if (E) {
				l.transform(t("jwarrow"), "rotate(180deg)")
			}
			k.style(w, {
				padding : (s.top + 1) + "px " + s.right + "px "
						+ (s.bottom + 1) + "px " + s.left + "px"
			});
			D.showing = false
		}
		function t(I) {
			return "#" + q + (I ? " ." + I : "")
		}
		function F(J, I) {
			var K = document.createElement("div");
			if (J) {
				K.className = J
			}
			if (I) {
				I.appendChild(K)
			}
			return K
		}
		function r(I, K) {
			var J = z(I), L = F(K, w);
			k.style(L, u(J));
			return [ L, J ]
		}
		function u(I) {
			return {
				background : "url(" + I.src + ") center",
				"background-size" : I.width + "px " + I.height + "px"
			}
		}
		function G(O, N) {
			if (!N) {
				N = ""
			}
			var K = r("cap" + O + N, "jwborder jw" + O + (N ? N : "")), I = K[0], L = K[1], J = l
					.extend(
							u(L),
							{
								width : (O === a || N === a || O === i || N === i) ? L.width
										: undefined,
								height : (O === d || N === d || O === j || N === j) ? L.height
										: undefined
							});
			J[O] = ((O === j && !E) || (O === d && E)) ? B.height : 0;
			if (N) {
				J[N] = 0
			}
			k.style(I, J);
			var M = {}, Q = {}, P = {
				left : L.width,
				right : L.width,
				top : (E ? B.height : 0) + L.height,
				bottom : (E ? 0 : B.height) + L.height
			};
			if (N) {
				M[N] = P[N];
				M[O] = 0;
				Q[O] = P[O];
				Q[N] = 0;
				k(t("jw" + O), M);
				k(t("jw" + N), Q);
				s[O] = P[O];
				s[N] = P[N]
			}
		}
		D.element = function() {
			return w
		};
		D.setContents = function(I) {
			l.empty(H);
			H.appendChild(I)
		};
		D.positionX = function(I) {
			k.style(w, {
				left : Math.round(I)
			})
		};
		D.positionY = function(I) {
			k.style(w, {
				bottom : I
			})
		};
		D.constrainX = function(I, J) {
			if (D.showing && I.width !== 0) {
				var K = D.offsetX(0);
				if (K) {
					if (J) {
						k.unblock()
					}
					var L = l.bounds(w);
					if (L.width !== 0) {
						if (L.right > I.right) {
							D.offsetX(I.right - L.right)
						} else {
							if (L.left < I.left) {
								D.offsetX(I.left - L.left)
							}
						}
					}
				}
			}
		};
		D.offsetX = function(J) {
			J = Math.round(J);
			var I = w.clientWidth;
			if (I !== 0) {
				k.style(w, {
					"margin-left" : Math.round(-I / 2) + J
				});
				k.style(o, {
					"margin-left" : Math.round(-B.width / 2) - J
				})
			}
			return I
		};
		D.borderWidth = function() {
			return s.left
		};
		function z(I) {
			var J = v.getSkinElement("tooltip", I);
			if (J) {
				return J
			} else {
				return {
					width : 0,
					height : 0,
					src : "",
					image : undefined,
					ready : false
				}
			}
		}
		D.show = function() {
			D.showing = true;
			k.style(w, {
				opacity : 1,
				visibility : "visible"
			})
		};
		D.hide = function() {
			D.showing = false;
			k.style(w, {
				opacity : 0,
				visibility : "hidden"
			})
		};
		x()
	};
	k(b, {
		position : "absolute",
		visibility : "hidden",
		opacity : 0
	});
	k(b + " .jwcontents", {
		position : "relative",
		"z-index" : 1
	});
	k(b + " .jwborder", {
		position : "absolute",
		"background-size" : "100% 100%"
	}, true);
	k(b + " .jwback", {
		position : "absolute",
		"background-size" : "100% 100%"
	});
	n(b, "opacity .25s, visibility .25s")
})(jwplayer);
(function(c) {
	var b = c.html5, a = c.utils;
	b.player = function(f) {
		var p = this, q, l, n, o, e;
		function r() {
			q = new b.model(f);
			p.id = q.id;
			p._model = q;
			a.css.block(p.id);
			l = new b.view(p, q);
			n = new b.controller(q, l);
			h();
			p.initializeAPI = h;
			o = new b.setup(q, l);
			o.addEventListener(c.events.JWPLAYER_READY, i);
			o.addEventListener(c.events.JWPLAYER_ERROR, k);
			o.start()
		}
		function i(s) {
			n.playerReady(s);
			a.css.unblock(p.id)
		}
		function k(s) {
			a.css.unblock(p.id);
			c(p.id).dispatchEvent(c.events.JWPLAYER_SETUP_ERROR, s)
		}
		function g() {
			var u = q.playlist, s = [];
			for (var t = 0; t < u.length; t++) {
				s.push(d(u[t]))
			}
			return s
		}
		function d(s) {
			var t = {
				description : s.description,
				file : s.file,
				image : s.image,
				mediaid : s.mediaid,
				title : s.title
			};
			a.foreach(s, function(u, v) {
				t[u] = v
			});
			t.sources = [];
			t.tracks = [];
			if (s.sources.length > 0) {
				a.foreach(s.sources, function(u, v) {
					var w = {
						file : v.file,
						type : v.type ? v.type : undefined,
						label : v.label,
						"default" : v["default"] ? true : false
					};
					t.sources.push(w)
				})
			}
			if (s.tracks.length > 0) {
				a.foreach(s.tracks, function(w, v) {
					var u = {
						file : v.file,
						kind : v.kind ? v.kind : undefined,
						label : v.label,
						"default" : v["default"] ? true : false
					};
					t.tracks.push(u)
				})
			}
			if (!s.file && s.sources.length > 0) {
				t.file = s.sources[0].file
			}
			return t
		}
		function h() {
			p.jwPlay = n.play;
			p.jwPause = n.pause;
			p.jwStop = n.stop;
			p.jwSeek = n.seek;
			p.jwSetVolume = n.setVolume;
			p.jwSetMute = n.setMute;
			p.jwLoad = n.load;
			p.jwPlaylistNext = n.next;
			p.jwPlaylistPrev = n.prev;
			p.jwPlaylistItem = n.item;
			p.jwSetFullscreen = n.setFullscreen;
			p.jwResize = l.resize;
			p.jwSeekDrag = q.seekDrag;
			p.jwGetQualityLevels = n.getQualityLevels;
			p.jwGetCurrentQuality = n.getCurrentQuality;
			p.jwSetCurrentQuality = n.setCurrentQuality;
			p.jwGetAudioTracks = n.getAudioTracks;
			p.jwGetCurrentAudioTrack = n.getCurrentAudioTrack;
			p.jwSetCurrentAudioTrack = n.setCurrentAudioTrack;
			p.jwGetCaptionsList = n.getCaptionsList;
			p.jwGetCurrentCaptions = n.getCurrentCaptions;
			p.jwSetCurrentCaptions = n.setCurrentCaptions;
			p.jwGetSafeRegion = l.getSafeRegion;
			p.jwForceState = l.forceState;
			p.jwReleaseState = l.releaseState;
			p.jwGetPlaylistIndex = j("item");
			p.jwGetPosition = j("position");
			p.jwGetDuration = j("duration");
			p.jwGetBuffer = j("buffer");
			p.jwGetWidth = j("width");
			p.jwGetHeight = j("height");
			p.jwGetFullscreen = j("fullscreen");
			p.jwGetVolume = j("volume");
			p.jwGetMute = j("mute");
			p.jwGetState = j("state");
			p.jwGetStretching = j("stretching");
			p.jwGetPlaylist = g;
			p.jwGetControls = j("controls");
			p.jwDetachMedia = n.detachMedia;
			p.jwAttachMedia = n.attachMedia;
			p.jwPlayAd = function(t) {
				var s = c(p.id).plugins;
				if (s.vast) {
					s.vast.jwPlayAd(t)
				}
			};
			p.jwPauseAd = function() {
				var s = c(p.id).plugins;
				if (s.googima) {
					s.googima.jwPauseAd()
				}
			};
			p.jwDestroyGoogima = function() {
				var s = c(p.id).plugins;
				if (s.googima) {
					s.googima.jwDestroyGoogima()
				}
			};
			p.jwInitInstream = function() {
				p.jwInstreamDestroy();
				e = new b.instream(p, q, l, n);
				e.init()
			};
			p.jwLoadItemInstream = function(t, s) {
				if (!e) {
					throw "Instream player undefined"
				}
				e.load(t, s)
			};
			p.jwLoadArrayInstream = function(t, s) {
				if (!e) {
					throw "Instream player undefined"
				}
				e.load(t, s)
			};
			p.jwSetControls = function(s) {
				l.setControls(s);
				if (e) {
					e.setControls(s)
				}
			};
			p.jwInstreamPlay = function() {
				if (e) {
					e.jwInstreamPlay()
				}
			};
			p.jwInstreamPause = function() {
				if (e) {
					e.jwInstreamPause()
				}
			};
			p.jwInstreamState = function() {
				if (e) {
					return e.jwInstreamState()
				}
				return ""
			};
			p.jwInstreamDestroy = function(s, t) {
				t = t || e;
				if (t) {
					t.jwInstreamDestroy(s || false);
					if (t === e) {
						e = undefined
					}
				}
			};
			p.jwInstreamAddEventListener = function(s, t) {
				if (e) {
					e.jwInstreamAddEventListener(s, t)
				}
			};
			p.jwInstreamRemoveEventListener = function(s, t) {
				if (e) {
					e.jwInstreamRemoveEventListener(s, t)
				}
			};
			p.jwPlayerDestroy = function() {
				if (n) {
					n.stop()
				}
				if (l) {
					l.destroy()
				}
				if (q) {
					q.destroy()
				}
				if (o) {
					o.resetEventListeners();
					o.destroy()
				}
			};
			p.jwInstreamSetText = function(s) {
				if (e) {
					e.jwInstreamSetText(s)
				}
			};
			p.jwIsBeforePlay = function() {
				return n.checkBeforePlay()
			};
			p.jwIsBeforeComplete = function() {
				return q.getVideo().checkComplete()
			};
			p.jwSetCues = l.addCues;
			p.jwAddEventListener = n.addEventListener;
			p.jwRemoveEventListener = n.removeEventListener;
			p.jwDockAddButton = l.addButton;
			p.jwDockRemoveButton = l.removeButton
		}
		function j(s) {
			return function() {
				return q[s]
			}
		}
		r()
	}
})(window.jwplayer);
(function(c) {
	var a = "#FFFFFF", b = "#CCCCCC", g = "#333333", e = "#999999", d = {
		size : 180,
		backgroundcolor : g,
		fontcolor : e,
		overcolor : b,
		activecolor : b,
		titlecolor : b,
		titleovercolor : a,
		titleactivecolor : a,
		fontweight : "normal",
		titleweight : "normal",
		fontsize : 11,
		titlesize : 13
	}, f = c.html5, l = c.events, j = c.utils, h = j.css, i = j.isMobile(), k = ".jwplaylist";
	f.playlistcomponent = function(F, Q) {
		var J = F, y = J.skin, p = j.extend({}, d, J.skin
				.getComponentSettings("playlist"), Q), K, z, n, r, x = -1, A, q, s = 76, t = {
			background : undefined,
			divider : undefined,
			item : undefined,
			itemOver : undefined,
			itemImage : undefined,
			itemActive : undefined
		}, w, L = this;
		L.element = function() {
			return K
		};
		L.redraw = function() {
			if (q) {
				q.redraw()
			}
		};
		L.show = function() {
			j.show(K)
		};
		L.hide = function() {
			j.hide(K)
		};
		function v() {
			K = O("div", "jwplaylist");
			K.id = J.id + "_jwplayer_playlistcomponent";
			w = (J._model.playlistlayout === "basic");
			z = O("div", "jwlistcontainer");
			P(K, z);
			N();
			if (w) {
				s = 32
			}
			if (t.divider) {
				s += t.divider.height
			}
			D();
			J.jwAddEventListener(l.JWPLAYER_PLAYLIST_LOADED, G);
			J.jwAddEventListener(l.JWPLAYER_PLAYLIST_ITEM, I);
			J.jwAddEventListener(l.JWPLAYER_RESIZE, o)
		}
		function o() {
			L.redraw()
		}
		function u(R) {
			return "#" + K.id + (R ? " ." + R : "")
		}
		function D() {
			var U = 0, T = 0, R = 0;
			j.clearCss(u());
			h(u(), {
				"background-color" : p.backgroundcolor
			});
			h(u("jwlist"), {
				"background-image" : t.background ? " url(" + t.background.src
						+ ")" : ""
			});
			h(u("jwlist *"), {
				color : p.fontcolor,
				font : p.fontweight + " " + p.fontsize
						+ "px Arial, Helvetica, sans-serif"
			});
			if (t.itemImage) {
				U = (s - t.itemImage.height) / 2 + "px ";
				T = t.itemImage.width;
				R = t.itemImage.height
			} else {
				T = s * 4 / 3;
				R = s
			}
			if (t.divider) {
				h(u("jwplaylistdivider"), {
					"background-image" : "url(" + t.divider.src + ")",
					"background-size" : "100% " + t.divider.height + "px",
					width : "100%",
					height : t.divider.height
				})
			}
			h(u("jwplaylistimg"), {
				height : R,
				width : T,
				margin : U ? (U + "0 " + U + U) : "0 5px 0 0"
			});
			h(u("jwlist li"), {
				"background-image" : t.item ? "url(" + t.item.src + ")" : "",
				height : s,
				overflow : "hidden",
				"background-size" : "100% " + s + "px",
				cursor : "pointer"
			});
			var S = {
				overflow : "hidden"
			};
			if (p.activecolor !== "") {
				S.color = p.activecolor
			}
			if (t.itemActive) {
				S["background-image"] = "url(" + t.itemActive.src + ")"
			}
			h(u("jwlist li.active"), S);
			h(u("jwlist li.active .jwtitle"), {
				color : p.titleactivecolor
			});
			h(u("jwlist li.active .jwdescription"), {
				color : p.activecolor
			});
			var V = {
				overflow : "hidden"
			};
			if (p.overcolor !== "") {
				V.color = p.overcolor
			}
			if (t.itemOver) {
				V["background-image"] = "url(" + t.itemOver.src + ")"
			}
			if (!i) {
				h(u("jwlist li:hover"), V);
				h(u("jwlist li:hover .jwtitle"), {
					color : p.titleovercolor
				});
				h(u("jwlist li:hover .jwdescription"), {
					color : p.overcolor
				})
			}
			h(u("jwtextwrapper"), {
				height : s,
				position : "relative"
			});
			h(u("jwtitle"), {
				overflow : "hidden",
				display : "inline-block",
				height : w ? s : 20,
				color : p.titlecolor,
				"font-size" : p.titlesize,
				"font-weight" : p.titleweight,
				"margin-top" : w ? "0 10px" : 10,
				"margin-left" : 10,
				"margin-right" : 10,
				"line-height" : w ? s : 20
			});
			h(u("jwdescription"), {
				display : "block",
				"font-size" : p.fontsize,
				"line-height" : 18,
				"margin-left" : 10,
				"margin-right" : 10,
				overflow : "hidden",
				height : 36,
				position : "relative"
			})
		}
		function C() {
			var R = O("ul", "jwlist");
			R.id = K.id + "_ul" + Math.round(Math.random() * 10000000);
			return R
		}
		function E(V) {
			var aa = n[V], Z = O("li", "jwitem"), S;
			Z.id = r.id + "_item_" + V;
			if (V > 0) {
				S = O("div", "jwplaylistdivider");
				P(Z, S)
			} else {
				var T = t.divider ? t.divider.height : 0;
				Z.style.height = (s - T) + "px";
				Z.style["background-size"] = "100% " + (s - T) + "px"
			}
			var W = O("div", "jwplaylistimg jwfill");
			var Y;
			if (aa["playlist.image"] && t.itemImage) {
				Y = aa["playlist.image"]
			} else {
				if (aa.image && t.itemImage) {
					Y = aa.image
				} else {
					if (t.itemImage) {
						Y = t.itemImage.src
					}
				}
			}
			if (Y && !w) {
				h("#" + Z.id + " .jwplaylistimg", {
					"background-image" : Y
				});
				P(Z, W)
			}
			var R = O("div", "jwtextwrapper");
			var X = O("span", "jwtitle");
			X.innerHTML = (aa && aa.title) ? aa.title : "";
			P(R, X);
			if (aa.description && !w) {
				var U = O("span", "jwdescription");
				U.innerHTML = aa.description;
				P(R, U)
			}
			P(Z, R);
			return Z
		}
		function O(S, R) {
			var T = document.createElement(S);
			if (R) {
				T.className = R
			}
			return T
		}
		function P(R, S) {
			R.appendChild(S)
		}
		function G() {
			z.innerHTML = "";
			n = H();
			if (!n) {
				return
			}
			r = C();
			for (var S = 0; S < n.length; S++) {
				var R = E(S);
				if (i) {
					var T = new j.touch(R);
					T.addEventListener(j.touchEvents.TAP, M(S))
				} else {
					R.onclick = M(S)
				}
				P(r, R)
			}
			x = J.jwGetPlaylistIndex();
			P(z, r);
			q = new f.playlistslider(K.id + "_slider", J.skin, K, r)
		}
		function H() {
			var S = J.jwGetPlaylist();
			var T = [];
			for (var R = 0; R < S.length; R++) {
				if (!S[R]["ova.hidden"]) {
					T.push(S[R])
				}
			}
			return T
		}
		function M(R) {
			return function() {
				A = R;
				J.jwPlaylistItem(R);
				J.jwPlay(true)
			}
		}
		function B() {
			var R = J.jwGetPlaylistIndex();
			if (R === A) {
				return
			}
			A = -1;
			if (q && q.visible()) {
				q.thumbPosition(R / (J.jwGetPlaylist().length - 1))
			}
		}
		function I(R) {
			if (x >= 0) {
				document.getElementById(r.id + "_item_" + x).className = "jwitem";
				x = R.index
			}
			document.getElementById(r.id + "_item_" + R.index).className = "jwitem active";
			B()
		}
		function N() {
			j.foreach(t, function(R) {
				t[R] = y.getSkinElement("playlist", R)
			})
		}
		v();
		return this
	};
	h(k, {
		position : "absolute",
		width : "100%",
		height : "100%"
	});
	j.dragStyle(k, "none");
	h(k + " .jwplaylistimg", {
		position : "relative",
		width : "100%",
		"float" : "left",
		margin : "0 5px 0 0",
		background : "#000",
		overflow : "hidden"
	});
	h(k + " .jwlist", {
		position : "absolute",
		width : "100%",
		"list-style" : "none",
		margin : 0,
		padding : 0,
		overflow : "hidden"
	});
	h(k + " .jwlistcontainer", {
		position : "absolute",
		overflow : "hidden",
		width : "100%",
		height : "100%"
	});
	h(k + " .jwlist li", {
		width : "100%"
	});
	h(k + " .jwtextwrapper", {
		overflow : "hidden"
	});
	h(k + " .jwplaylistdivider", {
		position : "absolute"
	});
	if (i) {
		j.transitionStyle(k + " .jwlist", "top .35s")
	}
})(jwplayer);
(function(i) {
	var s = jwplayer.utils, o = s.touchEvents, n = s.css, a = "jwslider", q = "jwslidertop", g = "jwsliderbottom", e = "jwrail", r = "jwrailtop", p = "jwrailback", l = "jwrailbottom", b = "jwthumb", v = "jwthumbtop", h = "jwthumbback", u = "jwthumbbottom", d = document, t = window, w, f = "absolute", j = "hidden", k = "100%";
	i.playlistslider = function(V, N, J, Z) {
		var O = N, aa = Z, ah, E, ae, S, ai = 0, W, af, C = s.isMobile(), ak, al = true, F, R, ad, z, ac, x, G, Q, U, ag, L;
		this.element = function() {
			return ah
		};
		this.visible = function() {
			return al
		};
		function I() {
			var an, am;
			ah = aj(a, null, J);
			ah.id = V;
			ak = new s.touch(aa);
			if (C) {
				ak.addEventListener(o.DRAG, Y)
			} else {
				ah.addEventListener("mousedown", D, false);
				ah.addEventListener("click", ab, false)
			}
			P();
			U = F.height;
			ag = R.height;
			n(X(), {
				width : ad.width
			});
			n(X(e), {
				top : U,
				bottom : ag
			});
			n(X(b), {
				top : U
			});
			an = aj(q, F, ah);
			am = aj(g, R, ah);
			E = aj(e, null, ah);
			ae = aj(b, null, ah);
			if (!C) {
				an.addEventListener("mousedown", y(-1), false);
				am.addEventListener("mousedown", y(1), false)
			}
			aj(r, z, E);
			aj(p, ad, E, true);
			aj(l, ac, E);
			n(X(p), {
				top : z.height,
				bottom : ac.height
			});
			aj(v, G, ae);
			aj(h, x, ae, true);
			aj(u, Q, ae);
			n(X(h), {
				top : G.height,
				bottom : Q.height
			});
			K();
			if (aa) {
				if (!C) {
					aa.addEventListener("mousewheel", B, false);
					aa.addEventListener("DOMMouseScroll", B, false)
				}
			}
		}
		function X(am) {
			return "#" + ah.id + (am ? " ." + am : "")
		}
		function aj(ap, ao, an, am) {
			var aq = d.createElement("div");
			if (ap) {
				aq.className = ap;
				if (ao) {
					n(X(ap), {
						"background-image" : ao.src ? ao.src : w,
						"background-repeat" : am ? "repeat-y" : "no-repeat",
						height : am ? w : ao.height
					})
				}
			}
			if (an) {
				an.appendChild(aq)
			}
			return aq
		}
		function P() {
			F = H("sliderCapTop");
			R = H("sliderCapBottom");
			ad = H("sliderRail");
			z = H("sliderRailCapTop");
			ac = H("sliderRailCapBottom");
			x = H("sliderThumb");
			G = H("sliderThumbCapTop");
			Q = H("sliderThumbCapBottom")
		}
		function H(am) {
			var an = O.getSkinElement("playlist", am);
			return an ? an : {
				width : 0,
				height : 0,
				src : w
			}
		}
		var K = this.redraw = function() {
			clearTimeout(L);
			L = setTimeout(function() {
				if (aa && aa.clientHeight) {
					T(aa.parentNode.clientHeight / aa.clientHeight)
				} else {
					L = setTimeout(K, 10)
				}
			}, 0)
		};
		function B(am) {
			if (!al) {
				return
			}
			am = am ? am : t.event;
			var an = am.detail ? am.detail * -1 : am.wheelDelta / 40;
			M(ai - an / 10);
			if (am.stopPropagation) {
				am.stopPropagation()
			}
			if (am.preventDefault) {
				am.preventDefault()
			} else {
				am.returnValue = false
			}
			am.cancelBubble = true;
			am.cancel = true;
			return false
		}
		function T(am) {
			if (am < 0) {
				am = 0
			}
			if (am > 1) {
				al = false
			} else {
				al = true;
				n(X(b), {
					height : Math.max(E.clientHeight * am, G.height + Q.height)
				})
			}
			n(X(), {
				visibility : al ? "visible" : j
			});
			if (aa) {
				aa.style.width = al ? aa.parentElement.clientWidth - ad.width
						+ "px" : ""
			}
		}
		var M = this.thumbPosition = function(am) {
			if (isNaN(am)) {
				am = 0
			}
			ai = Math.max(0, Math.min(1, am));
			n(X(b), {
				top : U + (E.clientHeight - ae.clientHeight) * ai
			});
			if (Z) {
				Z.style.top = Math.min(0, ah.clientHeight - Z.scrollHeight)
						* ai + "px"
			}
		};
		function D(am) {
			if (am.button === 0) {
				S = true
			}
			d.onselectstart = function() {
				return false
			};
			t.addEventListener("mousemove", ab, false);
			t.addEventListener("mouseup", A, false)
		}
		function Y(am) {
			M(ai - (am.deltaY * 2 / aa.clientHeight))
		}
		function ab(am) {
			if (S || am.type === "click") {
				var ar = s.bounds(E), ao = ae.clientHeight / 2, an = ar.height
						- ao, aq = am.pageY - ar.top, ap = (aq - ao)
						/ (an - ao);
				M(ap)
			}
		}
		function y(am) {
			return function(an) {
				if (an.button > 0) {
					return
				}
				M(ai + (am * 0.05));
				W = setTimeout(function() {
					af = setInterval(function() {
						M(ai + (am * 0.05))
					}, 50)
				}, 500)
			}
		}
		function A() {
			S = false;
			t.removeEventListener("mousemove", ab);
			t.removeEventListener("mouseup", A);
			d.onselectstart = w;
			clearTimeout(W);
			clearInterval(af)
		}
		I();
		return this
	};
	function c() {
		var x = [], y;
		for (y = 0; y < arguments.length; y++) {
			x.push(".jwplaylist ." + arguments[y])
		}
		return x.join(",")
	}
	n(c(a), {
		position : f,
		height : k,
		visibility : j,
		right : 0,
		top : 0,
		cursor : "pointer",
		"z-index" : 1,
		overflow : j
	});
	n(c(a) + " *", {
		position : f,
		width : k,
		"background-position" : "center",
		"background-size" : k + " " + k,
		overflow : j
	});
	n(c(q, r, v), {
		top : 0
	});
	n(c(g, l, u), {
		bottom : 0
	})
})(jwplayer.html5);
(function(e) {
	var k = jwplayer.utils, i = k.css, a = "About imooc", l = "http://www.imooc.com/about/us", j = document, h = ".jwclick", g = h
			+ "_item", f = "100%", d = "5px 5px 7px rgba(0,0,0,.10), 0px 1px 0px rgba(255,255,255,.3) inset", b = "none", c = "#FFF";
	e.rightclick = function(r, p) {
		var x = r, q, w = k.extend({
			aboutlink : l,
			abouttext : a + "...."
		}, p), n = false, y, s;
		function v() {
			q = j.getElementById(x.id);
			y = t(h);
			y.id = x.id + "_menu";
			y.style.display = b;
			q.oncontextmenu = o;
			y.onmouseover = function() {
				n = true
			};
			y.onmouseout = function() {
				n = false
			};
			j.addEventListener("mousedown", z, false);
			s = t(g);
			s.innerHTML = w.abouttext;
			s.onclick = u;
			y.appendChild(s);
			q.appendChild(y)
		}
		function t(A) {
			var B = j.createElement("div");
			B.className = A.replace(".", "");
			return B
		}
		function u() {
			window.top.location = w.aboutlink
		}
		function o(B) {
			var D, A, C;
			if (n) {
				return
			}
			B = B || window.event;
			D = B.target || B.srcElement;
			A = k.bounds(q);
			C = k.bounds(D);
			y.style.display = b;
			y.style.left = (B.offsetX ? B.offsetX : B.layerX) + C.left - A.left
					+ "px";
			y.style.top = (B.offsetY ? B.offsetY : B.layerY) + C.top - A.top
					+ "px";
			y.style.display = "block";
			B.preventDefault()
		}
		function z() {
			if (n) {
				return
			} else {
				y.style.display = b
			}
		}
		this.element = function() {
			return y
		};
		this.destroy = function() {
			j.removeEventListener("mousedown", z, false)
		};
		v()
	};
	i(h, {
		"background-color" : c,
		"-webkit-border-radius" : 5,
		"-moz-border-radius" : 5,
		"border-radius" : 5,
		height : "auto",
		border : "1px solid #bcbcbc",
		"font-family" : "'Microsoft YaHei', 'Geneva', sans-serif",
		"font-size" : 10,
		width : 320,
		"-webkit-box-shadow" : d,
		"-moz-box-shadow" : d,
		"box-shadow" : d,
		position : "absolute",
		"z-index" : 999
	}, true);
	i(h + " div", {
		padding : "8px 21px",
		margin : "0px",
		"background-color" : c,
		border : "none",
		"font-family" : "'Microsoft YaHei', 'Geneva', sans-serif",
		"font-size" : 10,
		color : "inherit"
	}, true);
	i(g, {
		padding : "8px 21px",
		"text-align" : "left",
		cursor : "pointer"
	}, true);
	i(g + ":hover", {
		"background-color" : "#595959",
		color : c
	}, true);
	i(g + " a", {
		"text-decoration" : b,
		color : "#000"
	}, true);
	i(h + " hr", {
		width : f,
		padding : 0,
		margin : 0,
		border : "1px #e9e9e9 solid"
	}, true)
})(jwplayer.html5);
(function(d) {
	var b = d.html5, a = d.utils, c = d._, e = d.events;
	b.setup = function(k, w) {
		var y = k, h = w, r, q = new e.eventdispatcher(), l = false;
		var G = {
			method : g,
			depends : []
		}, j = {
			method : F,
			depends : [ G ]
		}, E = {
			method : p,
			depends : [ G ]
		}, C = {
			method : D,
			depends : [ E, j ]
		}, s = {
			method : x,
			depends : [ C, E ]
		}, B = {
			method : u,
			depends : [ s ]
		};
		var n = [ G, j, E, C, s, B ];
		this.start = function() {
			c.defer(v)
		};
		function v() {
			if (this.cancelled) {
				return
			}
			for (var I = 0; I < n.length; I++) {
				var H = n[I];
				if (A(H.depends)) {
					n.splice(I, 1);
					H.method();
					c.defer(v)
				}
			}
		}
		function A(H) {
			return c.all(c.map(H, c.property("complete")))
		}
		function f(H) {
			H.complete = true;
			if (n.length > 0 && !l) {
				c.defer(v)
			}
		}
		function g() {
			if (k.edition && k.edition() === "invalid") {
				o("Error setting up player: Invalid license key")
			} else {
				f(G)
			}
		}
		function F() {
			r = new b.skin();
			r.load(y.config.skin, t, z)
		}
		function t() {
			f(j)
		}
		function z(H) {
			o("Error loading skin: " + H)
		}
		function p() {
			var H = a.typeOf(y.config.playlist);
			if (H === "array") {
				i(new d.playlist(y.config.playlist))
			} else {
				o("Playlist type not supported: " + H)
			}
		}
		function i(H) {
			y.setPlaylist(H);
			if (y.playlist.length === 0 || y.playlist[0].sources.length === 0) {
				o("Error loading playlist: No playable sources found")
			} else {
				f(E)
			}
		}
		function D() {
			h.setup(r);
			f(C)
		}
		function x() {
			f(s)
		}
		function u() {
			if (this.cancelled) {
				return
			}
			q.sendEvent(e.JWPLAYER_READY);
			f(B)
		}
		function o(H) {
			l = true;
			q.sendEvent(e.JWPLAYER_ERROR, {
				message : H
			});
			h.setupError(H)
		}
		this.destroy = function() {
			this.cancelled = true
		};
		a.extend(this, q)
	}
})(jwplayer);
(function(a) {
	a.skin = function() {
		var b = {};
		var d = false;
		this.load = function(g, f, e) {
			new a.skinloader(g, function(h) {
				d = true;
				b = h;
				if (typeof f === "function") {
					f()
				}
			}, function(h) {
				if (typeof e === "function") {
					e(h)
				}
			})
		};
		this.getSkinElement = function(e, f) {
			e = c(e);
			f = c(f);
			if (d) {
				try {
					return b[e].elements[f]
				} catch (g) {
					jwplayer.utils.log("No such skin component / element: ", [
							e, f ])
				}
			}
			return null
		};
		this.getComponentSettings = function(e) {
			e = c(e);
			if (d && b && b[e]) {
				return b[e].settings
			}
			return null
		};
		this.getComponentLayout = function(e) {
			e = c(e);
			if (d) {
				var f = b[e].layout;
				if (f && (f.left || f.right || f.center)) {
					return b[e].layout
				}
			}
			return null
		};
		function c(e) {
			return e.toLowerCase()
		}
	}
})(jwplayer.html5);
(function(b) {
	var a = jwplayer.utils, c = "Skin formatting error";
	b.skinloader = function(e, j, g) {
		var h = {}, l = j, s = g, q = true, t = e, f = false, v = jwplayer.utils
				.isMobile() ? 1 : 1, u = 1;
		function x() {
			if (typeof t !== "string" || t === "") {
				w(b.defaultskin())
			} else {
				if (a.extension(t) !== "xml") {
					s("Skin not a valid file type");
					return
				}
				new b.skinloader("", n, s)
			}
		}
		function n(y) {
			h = y;
			a.ajax(a.getAbsolutePath(t), function(z) {
				try {
					if (a.exists(z.responseXML)) {
						w(z.responseXML)
					}
				} catch (A) {
					s(c)
				}
			}, function(z) {
				s(z)
			})
		}
		function k(y, z) {
			return y ? y.getElementsByTagName(z) : null
		}
		function w(E) {
			var D = k(E, "skin")[0], M = k(D, "component"), X = D
					.getAttribute("target"), H = parseFloat(D
					.getAttribute("pixelratio"));
			if (H > 0) {
				u = H
			}
			if (!a.versionCheck(X)) {
				s("Incompatible player version")
			}
			if (M.length === 0) {
				l(h);
				return
			}
			for (var P = 0; P < M.length; P++) {
				var K = i(M[P].getAttribute("name")), J = {
					settings : {},
					elements : {},
					layout : {}
				}, O = k(k(M[P], "elements")[0], "element");
				h[K] = J;
				for (var N = 0; N < O.length; N++) {
					o(O[N], K)
				}
				var F = k(M[P], "settings")[0];
				if (F && F.childNodes.length > 0) {
					var R = k(F, "setting");
					for (var W = 0; W < R.length; W++) {
						var Y = R[W].getAttribute("name");
						var Q = R[W].getAttribute("value");
						if (/color$/.test(Y)) {
							Q = a.stringToColor(Q)
						}
						J.settings[i(Y)] = Q
					}
				}
				var S = k(M[P], "layout")[0];
				if (S && S.childNodes.length > 0) {
					var T = k(S, "group");
					for (var B = 0; B < T.length; B++) {
						var I = T[B], G = {
							elements : []
						};
						J.layout[i(I.getAttribute("position"))] = G;
						for (var V = 0; V < I.attributes.length; V++) {
							var L = I.attributes[V];
							G[L.name] = L.value
						}
						var U = k(I, "*");
						for (var A = 0; A < U.length; A++) {
							var z = U[A];
							G.elements.push({
								type : z.tagName
							});
							for (var C = 0; C < z.attributes.length; C++) {
								var y = z.attributes[C];
								G.elements[A][i(y.name)] = y.value
							}
							if (!a.exists(G.elements[A].name)) {
								G.elements[A].name = z.tagName
							}
						}
					}
				}
				q = false;
				d()
			}
		}
		function o(D, C) {
			C = i(C);
			var B = new Image(), y = i(D.getAttribute("name")), A = D
					.getAttribute("src"), F;
			if (A.indexOf("data:image/png;base64,") === 0) {
				F = A
			} else {
				var z = a.getAbsolutePath(t);
				var E = z.substr(0, z.lastIndexOf("/"));
				F = [ E, C, A ].join("/")
			}
			h[C].elements[y] = {
				height : 0,
				width : 0,
				src : "",
				ready : false,
				image : B
			};
			B.onload = function() {
				p(B, y, C)
			};
			B.onerror = function() {
				f = true;
				s("Skin image not found: " + this.src)
			};
			B.src = F
		}
		function d() {
			var A = true;
			for ( var y in h) {
				if (y !== "properties" && h.hasOwnProperty(y)) {
					var B = h[y].elements;
					for ( var z in B) {
						if (B.hasOwnProperty(z)) {
							A &= r(y, z).ready
						}
					}
				}
			}
			if (!A) {
				return
			}
			if (!q) {
				l(h)
			}
		}
		function p(z, B, A) {
			var y = r(A, B);
			if (y) {
				y.height = Math.round((z.height / u) * v);
				y.width = Math.round((z.width / u) * v);
				y.src = z.src;
				y.ready = true;
				d()
			} else {
				a.log("Loaded an image for a missing element: " + A + "." + B)
			}
		}
		function r(y, z) {
			return h[i(y)] ? h[i(y)].elements[i(z)] : null
		}
		function i(y) {
			return y ? y.toLowerCase() : ""
		}
		x()
	}
})(jwplayer.html5);
(function(c) {
	var b = c.html5, a = c.utils, d = c.events, e = a.css;
	b.thumbs = function(g) {
		var o, j, q, u, p = g, k, i = {}, n, h = new d.eventdispatcher();
		a.extend(this, h);
		o = document.createElement("div");
		o.id = p;
		function t(v) {
			e.style(o, {
				display : "none"
			});
			if (u) {
				u.onload = null;
				u.onreadystatechange = null;
				u.onerror = null;
				if (u.abort) {
					u.abort()
				}
				u = null
			}
			if (n) {
				n.onload = null
			}
			if (v) {
				q = v.split("?")[0].split("/").slice(0, -1).join("/");
				u = a.ajax(v, r, s, true)
			} else {
				j = k = n = null;
				i = {}
			}
		}
		function r(v) {
			u = null;
			try {
				v = new c.parsers.srt().parse(v.responseText, true)
			} catch (w) {
				s(w.message);
				return
			}
			if (a.typeOf(v) !== "array") {
				return s("Invalid data")
			}
			j = v
		}
		function s(v) {
			u = null;
			a.log("Thumbnails could not be loaded: " + v)
		}
		function f(w, B) {
			if (w && w !== k) {
				k = w;
				if (w.indexOf("://") < 0) {
					w = q ? q + "/" + w : w
				}
				var y = {
					display : "block",
					margin : "0 auto",
					"background-position" : "0 0",
					width : 0,
					height : 0
				};
				var x = w.indexOf("#xywh");
				if (x > 0) {
					try {
						var v = (/(.+)\#xywh=(\d+),(\d+),(\d+),(\d+)/).exec(w);
						w = v[1];
						y["background-position"] = (v[2] * -1) + "px "
								+ (v[3] * -1) + "px";
						y.width = v[4];
						y.height = v[5]
					} catch (A) {
						s("Could not parse thumbnail");
						return
					}
				}
				var z = i[w];
				if (!z) {
					z = new Image();
					z.onload = function() {
						l(z, y, B)
					};
					i[w] = z;
					z.src = w
				} else {
					l(z, y, B)
				}
				if (n) {
					n.onload = null
				}
				n = z
			}
		}
		function l(w, v, x) {
			w.onload = null;
			if (!v.width) {
				v.width = w.width;
				v.height = w.height
			}
			v["background-image"] = w.src;
			e.style(o, v);
			if (x) {
				x(v.width)
			}
		}
		this.load = function(v) {
			t(v)
		};
		this.element = function() {
			return o
		};
		this.updateTimeline = function(x, y) {
			if (!j) {
				return
			}
			var w = 0;
			while (w < j.length && x > j[w].end) {
				w++
			}
			if (w === j.length) {
				w--
			}
			var v = j[w].text;
			f(v, y);
			return v
		}
	}
})(jwplayer);
(function(k) {
	var q = k.jwplayer, l = q.html5, z = q.utils, c = q.events, i = c.state, t = z.css, u = z.bounds, x = z
			.isMobile(), g = z.isIPad(), C = z.isIPod(), r = "jwplayer", e = "aspectMode", d = "."
			+ r + ".jwfullscreen", s = "jwmain", B = "jwinstream", A = "jwvideo", f = "jwcontrols", b = "jwaspect", h = "jwplaylistcontainer", y = [
			"fullscreenchange", "webkitfullscreenchange",
			"mozfullscreenchange", "MSFullscreenChange" ], w = "opacity .25s ease", p = "100%", j = "absolute", v = " !important", n = "hidden", a = "none", o = "block";
	l.view = function(a8, H) {
		var br, bx, bh, ah, V, bk = -1, aW = x ? 4000 : 2000, aJ, aH, af, ao, aV, N, ad, aR = false, bn, aq, bE, aS, P, aN = z
				.extend({}, H.componentConfig("logo")), bj, R, a3, aC = false, aD = false, W = null, aL, aB, bw = -1, S = false, aF, K, ak, bD = false, aU = false, aa = z
				.extend(this, new c.eventdispatcher());
		var an = 0, X = 0;
		function bb() {
			br = bq("div", r + " playlist-" + H.playlistposition);
			br.id = a8.id;
			br.tabIndex = 0;
			K = br.requestFullscreen || br.webkitRequestFullscreen
					|| br.webkitRequestFullScreen || br.mozRequestFullScreen
					|| br.msRequestFullscreen;
			ak = document.exitFullscreen || document.webkitExitFullscreen
					|| document.webkitCancelFullScreen
					|| document.mozCancelFullScreen
					|| document.msExitFullscreen;
			bD = K && ak;
			if (H.aspectratio) {
				t.style(br, {
					display : "inline-block"
				});
				br.className = br.className.replace(r, r + " " + e)
			}
			var bK = document.getElementById(a8.id);
			bK.parentNode.replaceChild(br, bK);
			az();
			ag();
			document.getElementById(a8.id).focus();
			T();
			document.onmousemove = ba
		}
		function ba(bL) {
			if (a8.jwGetState() != i.IDLE) {
				an = X;
				bL = bL || k.event;
				var bK = bL.pageX || bL.clientX;
				X = bK
			}
		}
		function bG(bL) {
			var bK = z.between(H.position + bL, 0, this.getDuration());
			this.seek(bK);
			a0(bK)
		}
		var am;
		function bJ(bK) {
			var bL = z.between(this.getVolume() + bK, 0, 100);
			this.setVolume(bL);
			bn.showVolume();
			clearTimeout(am);
			am = setTimeout(F, 2000)
		}
		function bc(bK) {
			if (bK.ctrlKey || bK.metaKey) {
				return false
			}
			if (!H.controls) {
				return false
			}
			return true
		}
		function Q(bK) {
			if (!bc(bK)) {
				return true
			}
			if (!bn.adMode()) {
				J();
				aP()
			}
			var bM = q(a8.id);
			var bO = a8.jwGetState();
			switch (bK.keyCode) {
			case 27:
				bM.setFullscreen(false);
				break;
			case 13:
			case 32:
				if (bO != i.IDLE) {
					bM.play()
				}
				break;
			case 37:
				if (bO != i.IDLE) {
					if (!bn.adMode()) {
						bG.call(bM, -5)
					}
				}
				break;
			case 39:
				if (bO != i.IDLE) {
					if (!bn.adMode()) {
						bG.call(bM, 5)
					}
				}
				break;
			case 38:
				bJ.call(bM, 10);
				break;
			case 40:
				bJ.call(bM, -10);
				break;
			case 77:
				bM.setMute();
				break;
			case 70:
				bM.setFullscreen();
				break;
			default:
				if (bK.keyCode >= 48 && bK.keyCode <= 59) {
					var bN = bK.keyCode - 48;
					var bL = (bN / 10) * bM.getDuration();
					bM.seek(bL)
				}
				break
			}
			if (/13|32|37|38|39|40/.test(bK.keyCode)) {
				bK.preventDefault();
				return false
			}
		}
		var E;
		function a0(bN) {
			clearTimeout(E);
			E = setTimeout(bv, 2000);
			var bM = q(a8.id);
			var bP = bM.getDuration();
			var bO = aT(bN);
			var bL = aT(bP);
			var bK = Math.floor(bN / bP * 100);
			fastBox.style.visibility = "visible";
			fastBox.innerHTML = "进度:" + bK + "%(" + bO + "/" + bL + ")"
		}
		function aT(bK) {
			var bL = Math.floor(bK / 60);
			if (bL <= 9) {
				bL = "0" + String(bL)
			}
			m = Math.floor(bK % 60);
			if (m <= 9) {
				m = "0" + String(m)
			}
			return bL + ":" + m
		}
		function bv() {
			fastBox.style.visibility = "hidden"
		}
		function F() {
			bn.hideVolume()
		}
		function az() {
			fastBox = document.createElement("div");
			var bK = document.getElementById(a8.id);
			fastBox.style.backgroundColor = "#000000";
			fastBox.style.position = "absolute";
			fastBox.style.opacity = ".4";
			fastBox.style.font = "16px Microsoft YaHei,SimSun,Arial";
			fastBox.style.width = "220px";
			fastBox.style.height = "30px";
			fastBox.style.left = "20px";
			fastBox.style.top = "60px";
			fastBox.style.zIndex = "9999";
			fastBox.style.color = "#fff";
			fastBox.style.lineHeight = "30px";
			fastBox.style.textAlign = "center";
			fastBox.style.verticalAlign = "middle";
			fastBox.style.visibility = "hidden";
			bK.appendChild(fastBox)
		}
		var bp;
		function T() {
			bp = document.createElement("div");
			var bK = document.getElementById(a8.id);
			bp.style.backgroundColor = "#333333";
			bp.style.position = "absolute";
			bp.style.font = "16px Microsoft YaHei,SimSun,Arial";
			bp.style.height = "30px";
			bp.style.zIndex = "9999";
			bp.style.color = "#fff";
			bp.style.lineHeight = "30px";
			bp.style.textAlign = "left";
			bp.innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;" + H.videotitile;
			bp.style.visibility = "hidden";
			bK.appendChild(bp)
		}
		var L;
		function av() {
			clearInterval(L);
			ae();
			L = setInterval(function() {
				bu()
			}, 3000)
		}
		function ae() {
			bp.style.visibility = "visible";
			var bL = u(br);
			var bK = Math.round(bL.width);
			bp.style.width = bK + "px"
		}
		function bu() {
			clearInterval(L);
			bp.style.visibility = "hidden"
		}
		var bi, ap;
		function ag() {
			var bL = new Image();
			bL.src = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADwAAAA8CAYAAAA6/NlyAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAA25pVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMDY3IDc5LjE1Nzc0NywgMjAxNS8wMy8zMC0yMzo0MDo0MiAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDo0ZmE4ZmU1YS1lZmEyLWI0NGQtODNhYy1lYzE1NWI4MzNmYjEiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6RUM0QzIyOTY5MThEMTFFNThBQTNFOTdEMzE1NkM4NUYiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6RUM0QzIyOTU5MThEMTFFNThBQTNFOTdEMzE1NkM4NUYiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKFdpbmRvd3MpIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6REQ0NEVCQzUyRjg3MTFFNUEwMTZDMDZBRjlEMDUzNzIiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6REQ0NEVCQzYyRjg3MTFFNUEwMTZDMDZBRjlEMDUzNzIiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz7YPjWwAAAAdElEQVR42uzasQ2AMAxFwW/ErOwEy5ouTRoEBUXu1Y6lk9tUkjMLtWWxgIGBgYGBgYGBgYHftX953N3Hk7mquv7Y58LAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAo4rftMDAwMDAwMDAwMDAwHO3AAMAfw4Jq71QhG8AAAAASUVORK5CYII=";
			bL.style.width = "100%";
			bL.style.height = "100%";
			var bK = document.getElementById(a8.id);
			bi = document.createElement("div");
			bi.style.position = "absolute";
			bi.style.zIndex = "997";
			bi.style.left = "50%";
			bi.style.top = "50%";
			bi.appendChild(bL);
			bK.appendChild(bi);
			bi.style.visibility = "hidden";
			var bM = new Image();
			bM.src = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADwAAAA8CAYAAAA6/NlyAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAA25pVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMDY3IDc5LjE1Nzc0NywgMjAxNS8wMy8zMC0yMzo0MDo0MiAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDo0ZmE4ZmU1YS1lZmEyLWI0NGQtODNhYy1lYzE1NWI4MzNmYjEiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6QkNFNjM4NEI5MThEMTFFNTkzMDRCNzZDRjkwQ0Y5MTUiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6QkNFNjM4NEE5MThEMTFFNTkzMDRCNzZDRjkwQ0Y5MTUiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKFdpbmRvd3MpIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6REQ0NEVCQzUyRjg3MTFFNUEwMTZDMDZBRjlEMDUzNzIiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6REQ0NEVCQzYyRjg3MTFFNUEwMTZDMDZBRjlEMDUzNzIiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz5ZitpPAAACsElEQVR42uyaS08aURSAUYpAZSXjtrW4gkKXPKZrKcENRvAH8AgJz2hi/BUSQiBQ/gIEwitRu6sBgYEVOAEW1K55rCCBTqK9N6lJt7UDXLn3rGYz9+bLmfudc25mQyAQfBVgFJsCzIIAE2ACTIAJMAEmwASYABNgFIFrtZpRo9HIsAHWarV7DMPYwuGwWiwWb2LxSQNQUTAYpFmWtRweHlLYnGGFQkHl83lLJpOh5XK5CAtpbYI4OjpS93o9m9/v38PG0js7O7JIJGKsVqtGtVq9jU1Z0ul0UGonoVBILRKJNrCowxKJRHR6ekp3Oh2L2Wym1h74L6ntFgoFSzqdNixTaiutlVBqx8fHGig1r9f7HpvWEkotGo1+ubu7O1CpVNtrD/wcer3+Q7PZtEGpCYXCjbUH/iO1LSi1brdrMZlM1NoDP8f+/v5uqVSypFIpPZ9SQ3o8hFKzWq2fQAmzeTyed9jMwxRFyWKxmKlSqRwolcq32FwAGAwGKLWTy8vLjy+V2qu78ZBKpVtnZ2efs9ksjQXwbDb7BcpWBUxhlZe8/+Y1wYLG5IfT6aywLDtd6zM8Go0mPp/vmqbpb/8Di3yGH0GAs3rvdrsbAJrjY01kgfv9/gAMFLdXV1dDPtdFDhhIiYvH48zFxcU9x3FPfK+PFHC1Wn1wuVzldrs9XdQeSEhrPB5P/H7/NWgsbhYJu/IMQynlcjkWZJXhS0rIAgMpDQOBwHcwEQ2Xue/SgefzOZdIJJjz8/OFSAkp4Hq9/uBwOMqLPqcrlxaQ0jQYDN7odLqbVcIuPMPASU9ASrBTYgaDASdAIBYGDKUEsnpbLBYHKNV63oGhlJLJZANKCTw/otbJ8QrcaDR+2u32cqvVmqDao8NrEvK/NAEmwASYABNgAkyACTABJsD/Hr8FGAASoQ9aN88ErAAAAABJRU5ErkJggg==";
			bM.style.width = "100%";
			bM.style.height = "100%";
			ap = document.createElement("div");
			ap.appendChild(bM);
			bK.appendChild(ap);
			ap = document.createElement("div");
			ap.style.position = "absolute";
			ap.appendChild(bM);
			ap.style.zIndex = "999";
			ap.style.left = "50%";
			ap.style.top = "50%";
			bK.appendChild(ap);
			ap.style.visibility = "hidden"
		}
		var bm = true;
		function aY(bO) {
			if (bm) {
				bm = false;
				return
			}
			var bP = u(br);
			var bM = Math.round(bP.width);
			var bN = Math.round(bP.height);
			var bQ = 0, bL = 1, bK;
			if (bO == "play") {
				ap.style.marginLeft = "-30px";
				ap.style.marginTop = "-30px";
				bK = setInterval(function() {
					if (bQ >= 120) {
						ap.style.width = "60px";
						ap.style.height = "60px";
						ap.style.opacity = 1;
						ap.style.visibility = "hidden";
						clearInterval(bK);
						return
					}
					if (bQ == 20) {
						ap.style.visibility = "visible"
					}
					bQ += 10;
					bL -= 0.1;
					ap.style.width = bQ + "px";
					ap.style.marginLeft = (-parseInt(ap.style.width) / 2)
							+ "px";
					ap.style.height = bQ + "px";
					ap.style.marginTop = (-parseInt(ap.style.height) / 2)
							+ "px";
					ap.style.opacity = bL
				}, 50)
			} else {
				bi.style.marginLeft = "-30px";
				bi.style.marginTop = "-30px";
				bK = setInterval(function() {
					if (bQ >= 120) {
						bi.style.visibility = "hidden";
						clearInterval(bK);
						return
					}
					if (bQ == 20) {
						bi.style.visibility = "visible"
					}
					bQ += 10;
					bL -= 0.1;
					bi.style.width = bQ + "px";
					bi.style.marginLeft = (-parseInt(bi.style.width) / 2)
							+ "px";
					bi.style.height = bQ + "px";
					bi.style.marginTop = (-parseInt(bi.style.height) / 2)
							+ "px";
					bi.style.opacity = bL
				}, 50)
			}
		}
		function bd() {
			aU = true;
			aa.sendEvent(c.JWPLAYER_VIEW_TAB_FOCUS, {
				hasFocus : false
			})
		}
		function M() {
			var bK = !aU;
			aU = false;
			if (bK) {
				aa.sendEvent(c.JWPLAYER_VIEW_TAB_FOCUS, {
					hasFocus : true
				})
			}
		}
		function aO() {
			aU = false;
			aa.sendEvent(c.JWPLAYER_VIEW_TAB_FOCUS, {
				hasFocus : false
			})
		}
		this.getCurrentCaptions = function() {
			return bj.getCurrentCaptions()
		};
		this.setCurrentCaptions = function(bK) {
			bj.setCurrentCaptions(bK)
		};
		this.getCaptionsList = function() {
			return bj.getCaptionsList()
		};
		function a7() {
			var bK = u(br), bM = Math.round(bK.width), bL = Math
					.round(bK.height);
			if (!document.body.contains(br)) {
				k.removeEventListener("resize", a7);
				if (x) {
					k.removeEventListener("orientationchange", a7)
				}
			} else {
				if (bM && bL) {
					if (bM !== aH || bL !== af) {
						aH = bM;
						af = bL;
						if (aq) {
							aq.redraw()
						}
						clearTimeout(bw);
						bw = setTimeout(ay, 50);
						aa.sendEvent(c.JWPLAYER_RESIZE, {
							width : bM,
							height : bL
						})
					}
				}
			}
			return bK
		}
		this.setup = function(bN) {
			if (aC) {
				return
			}
			a8.skin = bN;
			bx = bq("span", s);
			bx.id = a8.id + "_view";
			aJ = bq("span", A);
			aJ.id = a8.id + "_media";
			bh = bq("span", f);
			ao = bq("span", B);
			V = bq("span", h);
			ah = bq("span", b);
			Z();
			bx.appendChild(aJ);
			bx.appendChild(bh);
			bx.appendChild(ao);
			br.appendChild(bx);
			br.appendChild(ah);
			br.appendChild(V);
			H.getVideo().setContainer(aJ);
			H.addEventListener("fullscreenchange", I);
			for (var bM = y.length; bM--;) {
				document.addEventListener(y[bM], I, false)
			}
			k.removeEventListener("resize", a7);
			k.addEventListener("resize", a7, false);
			if (x) {
				k.removeEventListener("orientationchange", a7);
				k.addEventListener("orientationchange", a7, false)
			}
			q(a8.id).onAdPlay(function() {
				bn.adMode(true);
				al(i.PLAYING);
				aP()
			});
			q(a8.id).onAdSkipped(function() {
				bn.adMode(false)
			});
			q(a8.id).onAdComplete(function() {
				bn.adMode(false)
			});
			q(a8.id).onAdError(function() {
				bn.adMode(false)
			});
			a8.jwAddEventListener(c.JWPLAYER_PLAYER_STATE, U);
			a8.jwAddEventListener(c.JWPLAYER_MEDIA_ERROR, be);
			a8.jwAddEventListener(c.JWPLAYER_PLAYLIST_COMPLETE, bo);
			a8.jwAddEventListener(c.JWPLAYER_PLAYLIST_ITEM, ar);
			a8.jwAddEventListener(c.JWPLAYER_CAST_AVAILABLE, function() {
				if (z.canCast()) {
					aa.forceControls(true)
				} else {
					aa.releaseControls()
				}
			});
			a8.jwAddEventListener(c.JWPLAYER_CAST_SESSION, function(bO) {
				if (!bE) {
					bE = new q.html5.castDisplay(a8.id);
					bE.statusDelegate = function(bP) {
						bE.setState(bP.newstate)
					}
				}
				if (bO.active) {
					t.style(bj.element(), {
						display : "none"
					});
					aa.forceControls(true);
					bE.setState("connecting").setName(bO.deviceName).show();
					a8.jwAddEventListener(c.JWPLAYER_PLAYER_STATE,
							bE.statusDelegate);
					a8.jwAddEventListener(c.JWPLAYER_CAST_AD_CHANGED, bH)
				} else {
					a8.jwRemoveEventListener(c.JWPLAYER_PLAYER_STATE,
							bE.statusDelegate);
					a8.jwRemoveEventListener(c.JWPLAYER_CAST_AD_CHANGED, bH);
					bE.hide();
					if (bn.adMode()) {
						a2()
					}
					t.style(bj.element(), {
						display : null
					});
					U({
						newstate : a8.jwGetState()
					});
					a7()
				}
			});
			U({
				newstate : i.IDLE
			});
			if (!x) {
				bh.addEventListener("mouseout", aE, false);
				bh.addEventListener("mousemove", bz, false);
				if (z.isMSIE()) {
					aJ.addEventListener("mousemove", bz, false);
					aJ.addEventListener("click", aq.clickHandler)
				}
			}
			ai(bn);
			ai(aS);
			ai(P);
			t("#" + br.id + "." + e + " ." + b, {
				"margin-top" : H.aspectratio,
				display : o
			});
			var bK = z.exists(H.aspectratio) ? parseFloat(H.aspectratio) : 100, bL = H.playlistsize;
			t("#" + br.id + ".playlist-right ." + b, {
				"margin-bottom" : -1 * bL * (bK / 100) + "px"
			});
			t("#" + br.id + ".playlist-right ." + h, {
				width : bL + "px",
				right : 0,
				top : 0,
				height : "100%"
			});
			t("#" + br.id + ".playlist-bottom ." + b, {
				"padding-bottom" : bL + "px"
			});
			t("#" + br.id + ".playlist-bottom ." + h, {
				width : "100%",
				height : bL + "px",
				bottom : 0
			});
			t("#" + br.id + ".playlist-right ." + s, {
				right : bL + "px"
			});
			t("#" + br.id + ".playlist-bottom ." + s, {
				bottom : bL + "px"
			});
			setTimeout(function() {
				Y(H.width, H.height)
			}, 0)
		};
		function ai(bK) {
			if (bK) {
				bK.element().addEventListener("mousemove", at, false);
				bK.element().addEventListener("mouseout", by, false)
			}
		}
		function a1() {
		}
		function aE() {
			clearTimeout(bk);
			bk = setTimeout(bB, aW)
		}
		function bq(bL, bK) {
			var bM = document.createElement(bL);
			if (bK) {
				bM.className = bK
			}
			return bM
		}
		function aA() {
			if (x) {
				if (aD) {
					bB()
				} else {
					aw()
				}
			} else {
				U({
					newstate : a8.jwGetState()
				})
			}
			if (aD) {
				aP()
			}
		}
		function aP() {
			clearTimeout(bk);
			bk = setTimeout(bB, aW)
		}
		function bz() {
			clearTimeout(bk);
			var bK = a8.jwGetState();
			if (bK === i.PLAYING || bK === i.PAUSED || aR) {
				if (a9 == X && bK != i.PAUSED) {
				} else {
					aw()
				}
				if (!S) {
					bk = setTimeout(bB, aW)
				}
			}
		}
		function at() {
			clearTimeout(bk);
			S = true
		}
		function by() {
			S = false
		}
		function a4(bK) {
			aa.sendEvent(bK.type, bK)
		}
		function Z() {
			var bL = H.componentConfig("controlbar"), bK = H
					.componentConfig("display");
			bj = new l.captions(a8, H.captions);
			bj.addEventListener(c.JWPLAYER_CAPTIONS_LIST, a4);
			bj.addEventListener(c.JWPLAYER_CAPTIONS_CHANGED, a4);
			bj.addEventListener(c.JWPLAYER_CAPTIONS_LOADED, a1);
			bh.appendChild(bj.element());
			aq = new l.display(a8, bK);
			aq.addEventListener(c.JWPLAYER_DISPLAY_CLICK, function(bM) {
				a4(bM);
				aA()
			});
			bh.appendChild(aq.element());
			P = new l.logo(a8, aN);
			bh.appendChild(P.element());
			aS = new l.dock(a8, H.componentConfig("dock"));
			bh.appendChild(aS.element());
			if (a8.edition && !x) {
				aB = new l.rightclick(a8, {
					abouttext : H.abouttext,
					aboutlink : H.aboutlink
				})
			} else {
				if (!x) {
					aB = new l.rightclick(a8, {})
				}
			}
			if (H.playlistsize && H.playlistposition
					&& H.playlistposition !== a) {
				R = new l.playlistcomponent(a8, {});
				V.appendChild(R.element())
			}
			bn = new l.controlbar(a8, bL, H.showset, H.shownextlevel);
			bn.addEventListener(c.JWPLAYER_USER_ACTION, aP);
			bh.appendChild(bn.element());
			if (C) {
				a5()
			}
			if (z.canCast()) {
				aa.forceControls(true)
			}
			br.onmousedown = bd;
			br.onfocusin = M;
			br.addEventListener("focus", M);
			br.onfocusout = aO;
			br.addEventListener("blur", aO);
			br.addEventListener("keydown", Q)
		}
		function bH(bK) {
			if (bK.done) {
				a2();
				return
			}
			if (!bK.complete) {
				if (!bn.adMode()) {
					a6()
				}
				bn.setText(bK.message);
				var bM = bK.onClick;
				if (bM !== undefined) {
					aq.setAlternateClickHandler(function() {
						bM(bK)
					})
				}
				var bL = bK.onSkipAd;
				if (bL !== undefined && bE) {
					bE.setSkipoffset(bK, bK.onSkipAd)
				}
			}
			if (bE) {
				bE.adChanged(bK)
			}
		}
		function a6() {
			bn.instreamMode(true);
			bn.adMode(true);
			bn.show(true)
		}
		function a2() {
			bn.setText("");
			bn.adMode(false);
			bn.instreamMode(false);
			bn.show(true);
			if (bE) {
				bE.adsEnded();
				bE.setState(a8.jwGetState())
			}
			aq.revertAlternateClickHandler()
		}
		var aK = this.fullscreen = function(bK) {
			if (!z.exists(bK)) {
				bK = !H.fullscreen
			}
			bK = !!bK;
			if (bK === H.fullscreen) {
				return
			}
			if (bD) {
				if (bK) {
					K.apply(br)
				} else {
					ak.apply(document)
				}
				aZ(br, bK)
			} else {
				if (z.isIE()) {
					aZ(br, bK)
				} else {
					if (ad) {
						ad.getVideo().setFullScreen(bK)
					}
					H.getVideo().setFullScreen(bK)
				}
			}
		};
		function bA(bK) {
			if (bK) {
				bK.redraw()
			}
		}
		function Y(bL, bS, bP) {
			var bO = br.className, bT, bN, bR, bM, bQ, bK = a8.id + "_view";
			t.block(bK);
			bP = !!bP;
			if (bP) {
				bO = bO.replace(/\s*aspectMode/, "");
				if (br.className !== bO) {
					br.className = bO
				}
				t.style(br, {
					display : o
				}, bP)
			}
			if (z.exists(bL) && z.exists(bS)) {
				H.width = bL;
				H.height = bS
			}
			bT = {
				width : bL
			};
			if (bO.indexOf(e) === -1) {
				bT.height = bS
			}
			t.style(br, bT, true);
			if (aq) {
				aq.redraw()
			}
			if (bn) {
				bn.redraw(true)
			}
			if (P) {
				P.offset(bn && P.position().indexOf("bottom") >= 0 ? bn
						.height()
						+ bn.margin() : 0);
				setTimeout(function() {
					if (aS) {
						aS
								.offset(P.position() === "top-left" ? P
										.element().clientWidth
										+ P.margin() : 0)
					}
				}, 500)
			}
			aj(bS);
			bM = H.playlistsize;
			bQ = H.playlistposition;
			if (R && bM && (bQ === "right" || bQ === "bottom")) {
				R.redraw();
				bN = {
					display : o
				};
				bR = {};
				bN[bQ] = 0;
				bR[bQ] = bM;
				if (bQ === "right") {
					bN.width = bM
				} else {
					bN.height = bM
				}
				t.style(V, bN);
				t.style(bx, bR)
			}
			ay(bL, bS);
			t.unblock(bK)
		}
		function aj(bK) {
			a3 = au(bK);
			if (bn) {
				if (a3) {
					bn.audioMode(true);
					aw();
					aq.hidePreview(true);
					D();
					bs(false)
				} else {
					bn.audioMode(false);
					al(a8.jwGetState())
				}
			}
			if (P && a3) {
				G()
			}
			br.style.backgroundColor = a3 ? "transparent" : "#000"
		}
		function au(bK) {
			if (H.aspectratio) {
				return false
			}
			if (q._.isNumber(bK)) {
				return aX(bK)
			}
			if (q._.isString(bK) && bK.indexOf("%") > -1) {
				return false
			}
			var bL = u(br);
			return aX(bL.height)
		}
		function aX(bK) {
			if (!bK) {
				return false
			}
			if (H.playlistposition === "bottom") {
				bK -= H.playlistsize
			}
			return bK <= 40
		}
		function ay(bL, bK) {
			if (!bL || isNaN(Number(bL))) {
				if (!aJ) {
					return
				}
				bL = aJ.clientWidth
			}
			if (!bK || isNaN(Number(bK))) {
				if (!aJ) {
					return
				}
				bK = aJ.clientHeight
			}
			if (z.isMSIE(9) && document.all && !k.atob) {
				bL = bK = "100%"
			}
			var bM = H.getVideo().resize(bL, bK, H.stretching);
			if (bM) {
				clearTimeout(bw);
				bw = setTimeout(ay, 250)
			}
		}
		this.resize = function(bM, bK) {
			var bL = true;
			Y(bM, bK, bL);
			a7()
		};
		this.resizeMedia = ay;
		var aM = this.completeSetup = function() {
			t.style(br, {
				opacity : 1
			});
			k.addEventListener("beforeunload", function() {
				if (!bI()) {
					a8.jwStop()
				}
			})
		};
		function ac() {
			if (bD) {
				var bK = document.fullscreenElement
						|| document.webkitCurrentFullScreenElement
						|| document.mozFullScreenElement
						|| document.msFullscreenElement;
				return !!(bK && bK.id === a8.id)
			}
			return aR ? ad.getVideo().getFullScreen() : H.getVideo()
					.getFullScreen()
		}
		var aG = false;
		function I(bK) {
			document.getElementById(a8.id).focus();
			aG = (bK.jwstate !== undefined) ? bK.jwstate : ac();
			if (bD) {
				aZ(br, aG)
			} else {
				bf(aG)
			}
			if (aG) {
				av()
			} else {
				bu()
			}
		}
		function aZ(bL, bK) {
			z.removeClass(bL, "jwfullscreen");
			if (bK) {
				z.addClass(bL, "jwfullscreen");
				t.style(document.body, {
					"overflow-y" : n
				});
				aP()
			} else {
				t.style(document.body, {
					"overflow-y" : ""
				})
			}
			bA(bn);
			bA(aq);
			bA(aS);
			ay();
			bf(bK)
		}
		function bf(bK) {
			H.setFullscreen(bK);
			if (ad) {
				ad.setFullscreen(bK)
			}
			if (bK) {
				clearTimeout(bw);
				bw = setTimeout(ay, 200)
			} else {
				if (g && a8.jwGetState() === i.PAUSED) {
					setTimeout(ax, 500)
				}
			}
		}
		var bC = "init";
		var a9 = 0;
		tempY = 0;
		function J() {
			if (bn && H.controls) {
				if (aR) {
					aV.show()
				} else {
					var bK = Math.abs(an - X);
					if (bK != 0 || a8.jwGetState() === i.PAUSED
							|| bC == "keydown") {
						an = X;
						bn.show()
					}
				}
			}
		}
		function a5() {
			if (W === true) {
				return
			}
			if (bn && !a3 && !H.getVideo().isAudioFile()) {
				if (aR) {
					aV.hide()
				}
				a9 = X;
				if (S == false) {
					bn.hide()
				}
			}
		}
		function ab() {
			if (aS && !a3 && H.controls) {
				aS.show()
			}
		}
		function aI() {
			if (aS && !aL && !H.getVideo().isAudioFile()) {
				aS.hide()
			}
		}
		function bg() {
			if (P && !a3) {
				P.show()
			}
		}
		function G() {
			if (P && (!H.getVideo().isAudioFile() || a3)) {
				P.hide(a3)
			}
		}
		function ax() {
			if (aq && H.controls && !a3) {
				if (!C || a8.jwGetState() === i.IDLE) {
					aq.show()
				}
			}
			if (!(x && H.fullscreen)) {
				H.getVideo().setControls(false)
			}
		}
		function D() {
			if (aq) {
				aq.hide()
			}
		}
		function bB() {
			clearTimeout(bk);
			if (W === true) {
				return
			}
			aD = false;
			var bK = a8.jwGetState();
			if (!H.controls || bK !== i.PAUSED) {
				a5()
			}
			if (!H.controls) {
				aI()
			}
			if (bK !== i.IDLE && bK !== i.PAUSED) {
				aI();
				G()
			}
			z.addClass(br, "jw-user-inactive")
		}
		function aw() {
			if (W === false) {
				return
			}
			aD = true;
			if (H.controls || a3) {
				J();
				ab()
			}
			if (aN.hide) {
				bg()
			}
			z.removeClass(br, "jw-user-inactive")
		}
		function bs(bK) {
			bK = bK && !a3;
			H.getVideo().setVisibility(bK)
		}
		function bo() {
			aL = true;
			aK(false);
			if (H.controls) {
				ab()
			}
		}
		function ar() {
			if (bE) {
				bE.setState(a8.jwGetState())
			}
		}
		var bl;
		function U(bK) {
			aL = false;
			clearTimeout(bl);
			bl = setTimeout(function() {
				al(bK.newstate)
			}, 100)
		}
		function be() {
			a5()
		}
		function O() {
			var bK = aR ? ad : H;
			return bK.getVideo().isAudioFile()
		}
		function bI() {
			return H.getVideo().isCaster
		}
		function al(bK) {
			aF = bK;
			if (bI()) {
				if (aq) {
					aq.show();
					aq.hidePreview(false)
				}
				t.style(aJ, {
					visibility : "visible",
					opacity : 1
				});
				if (bn) {
					bn.show("force control bar without audio check");
					bn.hideFullscreen(true)
				}
				return
			}
			switch (bK) {
			case i.PLAYING:
				if (H.getVideo().isCaster !== true) {
					W = null
				} else {
					W = true
				}
				if (O()) {
					bs(false);
					aq.hidePreview(a3);
					aq.setHiding(true);
					if (bn) {
						aw();
						bn.hideFullscreen(true)
					}
					ab()
				} else {
					bs(true);
					ay();
					aq.hidePreview(true);
					if (bn) {
						bn.hideFullscreen(!H.getVideo().supportsFullscreen())
					}
				}
				aY("play");
				break;
			case i.IDLE:
				bs(false);
				if (!a3) {
					aq.hidePreview(false);
					ax();
					ab();
					if (bn) {
						bn.hideFullscreen(false)
					}
				}
				break;
			case i.BUFFERING:
				ax();
				bB();
				if (x) {
					bs(true)
				}
				break;
			case i.PAUSED:
				ax();
				aw();
				aY("pause");
				break
			}
			bg()
		}
		function aQ(bK) {
			return "#" + a8.id + (bK ? " ." + bK : "")
		}
		this.setupInstream = function(bL, bK, bN, bM) {
			t.unblock();
			bF(aQ(B), true);
			bF(aQ(f), false);
			ao.appendChild(bL);
			aV = bK;
			N = bN;
			ad = bM;
			U({
				newstate : i.PLAYING
			});
			aR = true;
			ao.addEventListener("mousemove", bz);
			ao.addEventListener("mouseout", aE)
		};
		this.destroyInstream = function() {
			t.unblock();
			bF(aQ(B), false);
			bF(aQ(f), true);
			ao.innerHTML = "";
			ao.removeEventListener("mousemove", bz);
			ao.removeEventListener("mouseout", aE);
			aR = false
		};
		this.setupError = function(bK) {
			aC = true;
			q.embed.errorScreen(br, bK, H);
			aM()
		};
		function bF(bK, bL) {
			t(bK, {
				display : bL ? o : a
			})
		}
		this.addButton = function(bM, bK, bL, bN) {
			if (aS) {
				aS.addButton(bM, bK, bL, bN);
				if (a8.jwGetState() === i.IDLE) {
					ab()
				}
			}
		};
		this.removeButton = function(bK) {
			if (aS) {
				aS.removeButton(bK)
			}
		};
		this.setControls = function(bL) {
			var bK = !!bL;
			if (bK === H.controls) {
				return
			}
			H.controls = bK;
			if (aR) {
				bt(!bL)
			} else {
				if (bK) {
					U({
						newstate : a8.jwGetState()
					})
				}
			}
			if (!bK) {
				bB();
				D()
			}
			aa.sendEvent(c.JWPLAYER_CONTROLS, {
				controls : bK
			})
		};
		this.forceControls = function(bK) {
			W = !!bK;
			if (bK) {
				aw()
			} else {
				bB()
			}
		};
		this.releaseControls = function() {
			W = null;
			al(a8.jwGetState())
		};
		function bt(bK) {
			if (bK) {
				aV.hide();
				N.hide()
			} else {
				aV.show();
				N.show()
			}
		}
		this.addCues = function(bK) {
			if (bn) {
				bn.addCues(bK)
			}
		};
		this.forceState = function(bK) {
			aq.forceState(bK)
		};
		this.releaseState = function() {
			aq.releaseState(a8.jwGetState())
		};
		this.getSafeRegion = function(bL) {
			var bK = {
				x : 0,
				y : 0,
				width : 0,
				height : 0
			};
			bL = bL || !z.exists(bL);
			bn.showTemp();
			aS.showTemp();
			var bS = u(bx), bN = bS.top, bQ = aR ? u(document
					.getElementById(a8.id + "_instream_controlbar")) : u(bn
					.element()), bM = aR ? false : (aS.numButtons() > 0), bR = (P
					.position().indexOf("top") === 0), bP, bO = u(P.element());
			if (bM && H.controls) {
				bP = u(aS.element());
				bK.y = Math.max(0, bP.bottom - bN)
			}
			if (bR) {
				bK.y = Math.max(bK.y, bO.bottom - bN)
			}
			bK.width = bS.width;
			if (bQ.height && bL && H.controls) {
				bK.height = (bR ? bQ.top : bO.top) - bN - bK.y
			} else {
				bK.height = bS.height - bK.y
			}
			bn.hideTemp();
			aS.hideTemp();
			return bK
		};
		this.destroy = function() {
			k.removeEventListener("resize", a7);
			k.removeEventListener("orientationchange", a7);
			for (var bK = y.length; bK--;) {
				document.removeEventListener(y[bK], I, false)
			}
			H.removeEventListener("fullscreenchange", I);
			br.removeEventListener("keydown", Q, false);
			if (aB) {
				aB.destroy()
			}
			if (bE) {
				a8.jwRemoveEventListener(c.JWPLAYER_PLAYER_STATE,
						bE.statusDelegate);
				bE.destroy();
				bE = null
			}
			if (bh) {
				bh.removeEventListener("mousemove", bz);
				bh.removeEventListener("mouseout", aE)
			}
			if (aJ) {
				aJ.removeEventListener("mousemove", bz);
				aJ.removeEventListener("click", aq.clickHandler)
			}
			if (aR) {
				this.destroyInstream()
			}
		};
		bb()
	};
	t("." + r, {
		position : "relative",
		display : "block",
		opacity : 0,
		"min-height" : 0,
		"-webkit-transition" : w,
		"-moz-transition" : w,
		"-o-transition" : w
	});
	t("." + s, {
		position : j,
		left : 0,
		right : 0,
		top : 0,
		bottom : 0,
		"-webkit-transition" : w,
		"-moz-transition" : w,
		"-o-transition" : w
	});
	t("." + A + ", ." + f, {
		position : j,
		height : p,
		width : p,
		"-webkit-transition" : w,
		"-moz-transition" : w,
		"-o-transition" : w
	});
	t("." + A, {
		overflow : n,
		visibility : n,
		opacity : 0
	});
	t("." + A + " video", {
		background : "transparent",
		height : p,
		width : p,
		position : "absolute",
		margin : "auto",
		right : 0,
		left : 0,
		top : 0,
		bottom : 0
	});
	t("." + h, {
		position : j,
		height : p,
		width : p,
		display : a
	});
	t("." + B, {
		position : j,
		top : 0,
		left : 0,
		bottom : 0,
		right : 0,
		display : "none"
	});
	t("." + b, {
		display : "none"
	});
	t("." + r + "." + e, {
		height : "auto"
	});
	t(d, {
		width : p,
		height : p,
		left : 0,
		right : 0,
		top : 0,
		bottom : 0,
		"z-index" : 1000,
		margin : 0,
		position : "fixed"
	}, true);
	t(d + ".jw-user-inactive", {
		cursor : "none",
		"-webkit-cursor-visibility" : "auto-hide"
	});
	t(d + " ." + s, {
		left : 0,
		right : 0,
		top : 0,
		bottom : 0
	}, true);
	t(d + " ." + h, {
		display : a
	}, true);
	t("." + r + " .jwuniform", {
		"background-size" : "contain" + v
	});
	t("." + r + " .jwfill", {
		"background-size" : "cover" + v,
		"background-position" : "center"
	});
	t("." + r + " .jwexactfit", {
		"background-size" : p + " " + p + v
	})
})(window);