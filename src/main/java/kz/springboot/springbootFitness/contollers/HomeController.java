package kz.springboot.springbootFitness.contollers;

import kz.springboot.springbootFitness.entities.*;
import kz.springboot.springbootFitness.registration.RegistrationRequest;
import kz.springboot.springbootFitness.registration.RegistrationService;
import kz.springboot.springbootFitness.services.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private JymService jymService;

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private FreezingService freezingService;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private PricesService pricesService;

    @Value("${file.gympic.viewPath}")
    private String viewPath;

    @Value("${file.gympic.uploadPath}")
    private String uploadPath;

    @Value("${file.gympic.defaultPic}")
    private String defaultPic;

    @Value("${file.trainerpic.viewPath}")
    private String viewtrainerPath;

    @Value("${file.trainerpic.uploadPath}")
    private String uploadtrainerPath;

    @Value("${file.trainerpic.defaultPic}")
    private String defaulttrainerPic;

    @Autowired
    private AppUserService appUserService;

    @GetMapping(value = "/")
    public String index(Model model){

        List<Jyms> jyms = jymService.getAllJyms();
        model.addAttribute("jyms", jyms);
        model.addAttribute("current_user", getAppUserData());
        return "index";
    }

    @GetMapping(value = "/403")
    public String accessDenied(Model model){

        model.addAttribute("current_user", getAppUserData());
        return "403";
    }

    @GetMapping(value = "/addgym")
    @PreAuthorize("hasAnyRole('ROLE_BUSINESSADMIN','ROLE_ADMIN')")
    public String addGym(Model model){

        model.addAttribute("current_user", getAppUserData());
        List<Jyms> jyms = jymService.getAllJyms();
        model.addAttribute("jyms", jyms);
        return "addjym";
    }

    @PostMapping(value = "/addjym")
    @PreAuthorize("hasAnyRole('ROLE_BUSINESSADMIN','ROLE_ADMIN')")
    public String addJym(Model model,
                         @RequestParam(name = "jym_name", defaultValue = "No Jym") String name,
                         @RequestParam(name = "address", defaultValue = "No address") String address,
                         @RequestParam(name = "contact_numbers", defaultValue = "No contacts") String number,
                         @RequestParam(name = "description", defaultValue = "No description") String description){

        model.addAttribute("current_user", getAppUserData());
        Jyms jym = new Jyms();
        jym.setName(name);
        jym.setAddress(address);
        jym.setContact_number(number);
        jym.setDescription(description);
        jymService.addJym(jym);

        return "redirect:/";
    }

    @GetMapping(value = "/addfreezing")
    @PreAuthorize("hasAnyRole('ROLE_BUSINESSADMIN', 'ROLE_ADMIN')")
    public String freezings(Model model){

        model.addAttribute("current_user", getAppUserData());
        List<Freezing> freezings = freezingService.getAllFreezing();
        model.addAttribute("freezings", freezings);

        return "/addfreezing";
    }

    @PostMapping(value = "/addfreezing")
    @PreAuthorize("hasAnyRole('ROLE_BUSINESSADMIN', 'ROLE_ADMIN')")
    public String addFreezing(Model model,
                              @RequestParam(name = "freezing_name") String freezingName){

        model.addAttribute("current_user", getAppUserData());

        List<Freezing> freezings = freezingService.getAllFreezing();
        model.addAttribute("freezings", freezings);

        Freezing freezing = new Freezing();
        freezing.setName(freezingName);
        freezingService.addFreezing(freezing);

        return  "redirect:/addfreezing";
    }

    @GetMapping(value = "/addsubscription")
    @PreAuthorize("hasAnyRole('ROLE_BUSINESSADMIN', 'ROLE_ADMIN')")
    public String subscriptions(Model model){

        model.addAttribute("current_user", getAppUserData());
        List<Subscription> subscriptions = subscriptionService.getAllSubscriptions();
        model.addAttribute("subscriptions", subscriptions);

        return "addsubscription";
    }

    @PostMapping(value = "/addsubscription")
    @PreAuthorize("hasAnyRole('ROLE_BUSINESSADMIN', 'ROLE_ADMIN')")
    public String addSubscription(Model model,
                              @RequestParam(name = "subscription_name") String subscriptionName){

        model.addAttribute("current_user", getAppUserData());

        List<Subscription> subscriptions = subscriptionService.getAllSubscriptions();
        model.addAttribute("subscriptions", subscriptions);

        Subscription subscription = new Subscription();
        subscription.setName(subscriptionName);
        subscriptionService.addSubscription(subscription);

        return  "redirect:/addsubscription";
    }

    @GetMapping(value = "/gymdetails/{gymID}")
    public String gymDetails(Model model, @PathVariable(name = "gymID") Long id){

        model.addAttribute("current_user", getAppUserData());
        Jyms jym = jymService.getJym(id);

        model.addAttribute("jym", jym);
        return "gymdetails";

    }

    @GetMapping(value = "/gymdetailsedit/{gymID}")
    public String gymDetailsEdit(Model model, @PathVariable(name = "gymID") Long id){

        model.addAttribute("current_user", getAppUserData());

        Jyms jym = jymService.getJym(id);

        model.addAttribute("jym", jym);

        return "gymdetailsedit";

    }

    @GetMapping(value = "/gyms")
    public String gyms(Model model){

        model.addAttribute("current_user", getAppUserData());
        List<Jyms> jyms = jymService.getAllJyms();

        model.addAttribute("jyms", jyms);
        return "gyms";
    }

    @GetMapping(value = "/trainerdetailsedit/{trainerID}")
    public String trainerDetailsEdit(Model model, @PathVariable(name = "trainerID") Long id){

        model.addAttribute("current_user", getAppUserData());

        Trainers trainer = trainerService.getTrainer(id);
        List<Jyms> jyms = jymService.getAllJyms();

        model.addAttribute("jyms", jyms);

        model.addAttribute("trainer", trainer);

        return "trainerdetailsedit";

    }

    @PostMapping(value = "/edittrainer")
    @PreAuthorize("hasAnyRole('ROLE_BUSINESSADMIN','ROLE_ADMIN')")
    public String editTrainer(Model model,
                          @RequestParam(name = "id") Long id,
                          @RequestParam(name = "name") String name,
                          @RequestParam(name = "surname") String surname,
                          @RequestParam(name = "experience") int experience,
                          @RequestParam(name = "jym_id") Long gym_id,
                          @RequestParam(name = "info") String info,
                          @RequestParam(name = "trainer_pic") MultipartFile file){

        model.addAttribute("current_user", getAppUserData());
        Trainers trainer = trainerService.getTrainer(id);

        if(file.getContentType().equals("image/jpeg")||file.getContentType().equals("image/jpg")
                ||file.getContentType().equals("image/png")){

            try{

                String trainerPicName = DigestUtils.sha1Hex("trainerPic" + trainer.getId() + "_!Picture");

                byte[] bytes = file.getBytes();
                Path path = Paths.get(uploadtrainerPath + trainerPicName + ".jpg");
                Files.write(path, bytes);

                trainer.setTrainerPic(trainerPicName);

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        trainer.setName(name);
        trainer.setSurname(surname);
        trainer.setExperience(experience);
        trainer.setJym(jymService.getJym(gym_id));
        trainer.setInfo(info);

        trainerService.saveTrainer(trainer);

        return "redirect:/trainers";

    }

    @GetMapping(value = "/viewtrphoto/{url}", produces = {MediaType.IMAGE_JPEG_VALUE})
    public @ResponseBody byte[] viewTrainerPic(Model model, @PathVariable(name="url") String url) throws IOException {

        model.addAttribute("current_user", getAppUserData());
        String picURL = viewtrainerPath+defaulttrainerPic;

        if(url!=null){
            picURL = viewtrainerPath + url + ".jpg";
        }
        String picNull = "static/trainerpic/null.jpg";

        InputStream in;

        try{

            ClassPathResource resource = new ClassPathResource(picURL);
            in = resource.getInputStream();

        }catch (Exception e){

            ClassPathResource resource = new ClassPathResource(viewtrainerPath+defaulttrainerPic);
            in = resource.getInputStream();
            e.printStackTrace();
        }

        return IOUtils.toByteArray(in);

    }

    @GetMapping(value = "/addprice")
    @PreAuthorize("hasAnyRole('ROLE_BUSINESSADMIN', 'ROLE_ADMIN')")
    public String addPrice(Model model){

        model.addAttribute("current_user", getAppUserData());
        List<Jyms> jyms = jymService.getAllJyms();
        List<Subscription> subscriptions = subscriptionService.getAllSubscriptions();
        List<Freezing> freezings = freezingService.getAllFreezing();

        List<Prices> pricesJem = pricesService.getAllPrices();//jym.getPrices();

        model.addAttribute("pricesJem", pricesJem);
        model.addAttribute("subscriptions", subscriptions);
        model.addAttribute("freezings", freezings);
        model.addAttribute("jyms", jyms);
        return "addprice";
    }

    @GetMapping(value = "/prices")
    public String subPrices(Model model){
        model.addAttribute("current_user", getAppUserData());

        List<Jyms> jyms = jymService.getAllJyms();

        List<Jyms> jymsWithPrice = jymService.getWithPrice();


        List<List<Prices>> prices = new ArrayList<List<Prices>>();

        for (Jyms jym: jymsWithPrice
             ) {
            try{
                if((jym.getPrices()!=null && !jym.getPrices().isEmpty())){
                    System.out.println("entered jym:jyms");
                    prices.add(jym.getPrices());
                }
            }catch (Exception e){
                e.printStackTrace();
            }

//            prices.add(jym.getPrices());
        }

        model.addAttribute("jymsWithPrice", jymsWithPrice);

        return "price";

    }

    @PostMapping(value = "/addprice")
    @PreAuthorize("hasAnyRole('ROLE_BUSINESSADMIN', 'ROLE_ADMIN')")
    public String addPricePost(Model model,
                               @RequestParam(name = "gym_id") Long gymID,
                               @RequestParam(name = "subscription_id") Long subscriptionId,
                               @RequestParam(name = "freezing_id") Long freezingId,
                               @RequestParam(name = "price") int price){

        model.addAttribute("current_user", getAppUserData());

        Prices priceObj = new Prices();

        Jyms jyms = jymService.getJym(gymID);
        Subscription subscription = subscriptionService.getSubscription(subscriptionId);
        Freezing freezing = freezingService.getFreezing(freezingId);

        priceObj.setJym(jyms);
        priceObj.setSubscription(subscription);
        priceObj.setFreezing(freezing);
        priceObj.setPrice(price);

        pricesService.savePrice(priceObj);

        return  "redirect:/addprice";
    }

    @GetMapping(value = "/price")
    public String price(Model model){

        model.addAttribute("current_user", getAppUserData());
        List<Jyms> jyms = jymService.getAllJyms();

        model.addAttribute("jyms", jyms);
        return "price";
    }

    @PostMapping(value = "/editgym")
    @PreAuthorize("hasAnyRole('ROLE_BUSINESSADMIN','ROLE_ADMIN')")
    public String editGym(Model model,
                          @RequestParam(name = "id") Long id,
                          @RequestParam(name = "jym_name") String name,
                          @RequestParam(name = "address") String address,
                          @RequestParam(name = "contact_numbers") String contact_numbers,
                          @RequestParam(name = "description") String description,
                          @RequestParam(name = "gym_pic") MultipartFile file){

        model.addAttribute("current_user", getAppUserData());
        Jyms jym = jymService.getJym(id);

        if(file.getContentType().equals("image/jpeg")||file.getContentType().equals("image/jpg")
                ||file.getContentType().equals("image/png")){

            try{

                String gymPicName = DigestUtils.sha1Hex("gymPic" + jym.getId() + "_!Picture");

                byte[] bytes = file.getBytes();
                Path path = Paths.get(uploadPath + gymPicName + ".jpg");
                Files.write(path, bytes);

                jym.setGymPic(gymPicName);

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        jym.setName(name);
        jym.setAddress(address);
        jym.setContact_number(contact_numbers);
        jym.setDescription(description);

        jymService.saveJym(jym);

        return "redirect:/gyms";

    }


    @GetMapping(value = "/viewphoto/{url}", produces = {MediaType.IMAGE_JPEG_VALUE})
    public @ResponseBody byte[] viewGymPic(Model model, @PathVariable(name="url") String url) throws IOException {

        model.addAttribute("current_user", getAppUserData());
        String picURL = viewPath+defaultPic;

        if(url!=null){
            picURL = viewPath + url + ".jpg";
        }

        InputStream in;

        try{

            ClassPathResource resource = new ClassPathResource(picURL);
            in = resource.getInputStream();

        }catch (Exception e){

            ClassPathResource resource = new ClassPathResource(viewPath+defaultPic);
            in = resource.getInputStream();
            e.printStackTrace();
        }

        return IOUtils.toByteArray(in);

    }

    @PostMapping(value = "/deletegym")
    @PreAuthorize("hasAnyRole('ROLE_BUSINESSADMIN','ROLE_ADMIN')")
    public String deleteGym(Model model,
                            @RequestParam(name = "id") Long id){

        model.addAttribute("current_user", getAppUserData());

        jymService.deleteJym(jymService.getJym(id));
        return "redirect:/";
    }

    @GetMapping(value = "/trainers")
    public String trainers(Model model){

        model.addAttribute("current_user", getAppUserData());

        List<Trainers> trainers = trainerService.getAllTrainers();

        model.addAttribute("trainers", trainers);
        return "trainers";
    }

    @GetMapping(value = "/addtrainer")
    @PreAuthorize("hasAnyRole('ROLE_BUSINESSADMIN','ROLE_ADMIN')")
    public String addTrainer(Model model){
        model.addAttribute("current_user", getAppUserData());

        List<Trainers> trainers = trainerService.getAllTrainers();
        List<Jyms> jyms = jymService.getAllJyms();

        model.addAttribute("jyms", jyms);

        model.addAttribute("trainers", trainers);
        return "addtrainer";
    }

    @PostMapping(value = "/addtrainer")
    @PreAuthorize("hasAnyRole('ROLE_BUSINESSADMIN','ROLE_ADMIN')")
    public String addTrainer(Model model,
                             @RequestParam(name = "name") String name,
                             @RequestParam(name = "surname") String surname,
                             @RequestParam(name = "experience") int experience,
                             @RequestParam(name = "jym_id") Long gymId,
                             @RequestParam(name = "achievements") String achievements){

        model.addAttribute("current_user", getAppUserData());
        Trainers trainerAdd = new Trainers();
        trainerAdd.setName(name);
        trainerAdd.setSurname(surname);
        trainerAdd.setExperience(experience);
        trainerAdd.setInfo(achievements);

        Jyms jym = jymService.getJym(gymId);
        trainerAdd.setJym(jym);

        trainerService.saveTrainer(trainerAdd);

        return "redirect:/trainers";
    }

    @GetMapping(value = "/trainerdetails/{trainerID}")
    public String trainerDetails(Model model, @PathVariable(name = "trainerID") Long id){

        Trainers trainer = trainerService.getTrainer(id);
        model.addAttribute("trainer", trainer);
        model.addAttribute("current_user", getAppUserData());

        return "trainerdetails";

    }

    @PostMapping(value = "/deletetrainer")
    @PreAuthorize("hasAnyRole('ROLE_BUSINESSADMIN','ROLE_ADMIN')")
    public String deleteTrainer(Model model,
                                @RequestParam(name = "trainer_id") Long trainer_id){
        model.addAttribute("current_user", getAppUserData());
        trainerService.deleteTrainer(trainerService.getTrainer(trainer_id));

        return "redirect:/trainers";
    }
    private RegistrationService registrationService;

    @GetMapping(value = "/api/v1/registration")
    public String register(@RequestBody RegistrationRequest request) {

//        return registrationService.register(request);

        return "addtrainer";

    }

    private AppUser getAppUserData(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            AppUser appUser = (AppUser) authentication.getPrincipal();
//            AppUser appUser = (AppUser) appUserService.loadUserByUsername(secUser);
            return appUser;
        }
        return null;
    }

    @GetMapping(value = "/login")
    public String login(Model model){
        return "login";
    }

    @GetMapping(value = "/profile")
    @PreAuthorize("isAuthenticated()")
    public String profile(Model model){
        model.addAttribute("current_user", getAppUserData());
        return "profile";
    }

}