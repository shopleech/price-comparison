package com.shopleech.publicapi;

import org.junit.jupiter.api.Test;

//@ExtendWith(SpringExtension.class)
//@WebAppConfiguration
//@ContextConfiguration(classes = {WebMvcConfiguration.class})
class HelloControllerTest {

//	@Autowired
//	private WebApplicationContext wac;
//
//	private MockMvc mockMvc;

//	@BeforeEach
//	void setup() {
//		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
//	}

	@Test
	public void testDefaultPage() throws Exception {

//		MvcResult result = this.mockMvc.perform(get("/"))
//				/*.andDo(print())*/
//				.andExpect(status().isOk())
//				.andReturn();

//		ModelAndView modelAndView = result.getModelAndView();
//		assertEquals("welcome", modelAndView.getViewName());
//		assertEquals("Spring MVC Hello World", modelAndView.getModel().get("message"));

	}

	@Test
	public void testHelloPage() throws Exception {

//		MvcResult result = this.mockMvc.perform(get("/hello/mkyong"))
//				.andExpect(status().isOk())
//				.andReturn();

//		ModelAndView modelAndView = result.getModelAndView();
//		assertEquals("welcome", modelAndView.getViewName());
//		assertEquals("mkyong", modelAndView.getModel().get("message"));

	}

}
