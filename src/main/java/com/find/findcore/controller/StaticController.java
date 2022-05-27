package com.find.findcore.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/static")
public class StaticController {
	private static final Logger log = LoggerFactory.getLogger(StaticController.class);

	@GetMapping({ "/" })
	public String getHealth() {
		return "Let us help you FIND your dream home!";
	}

	@GetMapping({ "/home" })
	public String getHome() {
		return "Let us help you FIND your dream home!";
	}

	@GetMapping(value = "/privacyPolicy", produces = "text/html")
	public ModelAndView privacyPolicy(Model model) {
		return new ModelAndView("PrivacyPolicy");
	}

	@GetMapping(value = "/faq", produces = "application/json")
	public String fetchJson() {
		String json = "[\n" + "   {\n" + "      \"question\":\"What is FIND?\",\n"
				+ "      \"answer\":\"FIND is a free to download entertainment aggregator. It assists you to search and discover movies, shows and events you might like and are legally available with the providers. FIND also recommends you what to watch or which event to go to based on your preferences.\\nFIND also has a proprietary music identification service - FINDED! which identifies any song playing near you with just a Tap!\\nFIND is world's first 360° entertainment aggregator, Made in India!\"\n"
				+ "   },\n" + "   {\n" + "      \"question\":\"Is FIND available in all countries?\",\n"
				+ "      \"answer\":\"Not yet, but we are working on expanding to other territories and will be available soon.\\nFIND is currently available in India only.\"\n"
				+ "   },\n" + "   {\n" + "      \"question\":\"Is FIND free?\",\n"
				+ "      \"answer\":\"Absolutely! FIND is available for free for you to download and use all its features including FINDED!\"\n"
				+ "   },\n" + "   {\n"
				+ "      \"question\":\"How is FIND collecting data of movies, shows and events and their availability?\",\n"
				+ "      \"answer\":\"We use open source database for information about a title such as posters, casts, directors, synopsis etc. We then use this database to match the titles or events available at different platforms which we update regularly.\"\n"
				+ "   },\n" + "   {\n"
				+ "      \"question\":\"I could not find a title or event when I searched on FIND. Why?\",\n"
				+ "      \"answer\":\"It may be because of two reasons:\\n1. That particular title may not be available with any of the providers.\\n2. Or our team has not have it updated in our database. If this is the case, please be assured we are working round the clock to rectify it.\"\n"
				+ "   },\n" + "   {\n" + "      \"question\":\"Is content different in each country?\",\n"
				+ "      \"answer\":\"Yes, most services has different content in different country. For example: A movie available on Netflix in India might be available on Hulu in US. We curate our content based on each country.\"\n"
				+ "   },\n" + "   {\n"
				+ "      \"question\":\"Do you know upcoming movies, shows or events which are yet to be listed?\",\n"
				+ "      \"answer\":\"We will have this information only when a provider shares this with us either through advertisement or through FIND's partnership with the provider/s. Rest assured, we will share as much information as possible with you which we legally can.\"\n"
				+ "   },\n" + "   {\n"
				+ "      \"question\":\"I was redirected to a different page after click on the content or event provider icon. I am confused as why this has happened?\",\n"
				+ "      \"answer\":\"If this has happened, we are very sorry as this is our fault. We probably mixed up the titles in our database. We are working around the clock to make sure we make your experience as perfect as possible.\"\n"
				+ "   },\n" + "   {\n"
				+ "      \"question\":\"How can I list my streaming service or event service with FIND?\",\n"
				+ "      \"answer\":\"We are continuously working on adding new service providers to our service. Please get in touch with us at privacy@find.live.\"\n"
				+ "   },\n" + "   {\n"
				+ "      \"question\":\"I have a great idea and suggestion on how you can make FIND better. How can I get in touch with you?\",\n"
				+ "      \"answer\":\"We have been searching for you for your valuable feedback. Write to us info@find.live with the email title - 'Aishwary, this is how you can make the app better!'\"\n"
				+ "   }\n" + "]";
		return json;
	}

	@GetMapping(value = "/profileAvatar")
	public List<String> getProfileAvatarImages() {
		List<String> profAvtr = new ArrayList<>();
		profAvtr.add("https://findavatarimages.s3.ap-east-1.amazonaws.com/default.png");
		profAvtr.add("https://findavatarimages.s3.ap-east-1.amazonaws.com/bald.png");
		profAvtr.add("https://findavatarimages.s3.ap-east-1.amazonaws.com/beanie-2_a.png");
		profAvtr.add("https://findavatarimages.s3.ap-east-1.amazonaws.com/beanie_a.png");
		profAvtr.add("https://findavatarimages.s3.ap-east-1.amazonaws.com/beard_black.png");
		profAvtr.add("https://findavatarimages.s3.ap-east-1.amazonaws.com/curly-hair-2-copy.png");
		profAvtr.add("https://findavatarimages.s3.ap-east-1.amazonaws.com/curly-hair-3-copy.png");
		profAvtr.add("https://findavatarimages.s3.ap-east-1.amazonaws.com/fringe-copy.png");
		profAvtr.add("https://findavatarimages.s3.ap-east-1.amazonaws.com/glasses_a.png");
		profAvtr.add("https://findavatarimages.s3.ap-east-1.amazonaws.com/glasses-2_a.png");
		profAvtr.add("https://findavatarimages.s3.ap-east-1.amazonaws.com/goatee_grey.png");
		profAvtr.add("https://findavatarimages.s3.ap-east-1.amazonaws.com/Grey_glasses.png");
		profAvtr.add("https://findavatarimages.s3.ap-east-1.amazonaws.com/Grey.png");
		profAvtr.add("https://findavatarimages.s3.ap-east-1.amazonaws.com/happy-2_a.png");
		profAvtr.add("https://findavatarimages.s3.ap-east-1.amazonaws.com/happy_a.png");
		profAvtr.add("https://findavatarimages.s3.ap-east-1.amazonaws.com/happy_brown.png");
		profAvtr.add("https://findavatarimages.s3.ap-east-1.amazonaws.com/happy-face_a.png");
		profAvtr.add("https://findavatarimages.s3.ap-east-1.amazonaws.com/happy-face-2-copy.png");
		profAvtr.add("https://findavatarimages.s3.ap-east-1.amazonaws.com/headband_a.png");
		profAvtr.add("https://findavatarimages.s3.ap-east-1.amazonaws.com/long-hair_a2.png");
		profAvtr.add("https://findavatarimages.s3.ap-east-1.amazonaws.com/long-hair-2_a.png");
		profAvtr.add("https://findavatarimages.s3.ap-east-1.amazonaws.com/long-hair-3_a.png");
		profAvtr.add("https://findavatarimages.s3.ap-east-1.amazonaws.com/long-hair-4_a.png");
		profAvtr.add("https://findavatarimages.s3.ap-east-1.amazonaws.com/long-hair-5_a.png");
		profAvtr.add("https://findavatarimages.s3.ap-east-1.amazonaws.com/model_a.png");
		profAvtr.add("https://findavatarimages.s3.ap-east-1.amazonaws.com/model-2_a.png");
		profAvtr.add("https://findavatarimages.s3.ap-east-1.amazonaws.com/model-3-copy_boy.png");
		profAvtr.add("https://findavatarimages.s3.ap-east-1.amazonaws.com/model-3-copy.png");
		profAvtr.add("https://findavatarimages.s3.ap-east-1.amazonaws.com/model-4_a.png");
		profAvtr.add("https://findavatarimages.s3.ap-east-1.amazonaws.com/pink.png");
		profAvtr.add("https://findavatarimages.s3.ap-east-1.amazonaws.com/ponytail-2_a.png");
		profAvtr.add("https://findavatarimages.s3.ap-east-1.amazonaws.com/ponytail_a.png");
		profAvtr.add("https://findavatarimages.s3.ap-east-1.amazonaws.com/short-hair_a.png");
		return profAvtr;
	}
}