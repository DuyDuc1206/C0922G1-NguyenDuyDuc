import {Component, HostListener, OnInit} from '@angular/core';
import {ActivatedRoute, NavigationEnd, Router} from '@angular/router';
import {filter} from 'rxjs/operators';
import {TokenService} from '../../service/token.service';
import {ShareService} from '../../service/share.service';
import {User} from '../../model/user';
import {CartService} from '../../service/cart/cart.service';
import {Cart} from '../../model/cart';
import {AuthService} from '../../service/auth.service';
import {ICart} from '../../model/i-cart';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  isNotFoundPage: boolean = false;
  isCourseDetailPage = false;
  isLessonPage = false;
  isHomePageAndSignAndError = false;
  isSignInPage = false;
  isSignUpPage = false;
  isLogined = false;
  name: string;
  username: string;
  role?: string;
  user: User;
  nameSearch: string;
  isScrolled = false;
  id: number = 0;
  totalCart: number = 0;
  carts: ICart[] = [];

  @HostListener('window:scroll', [])
  onWindowScroll() {
    this.isScrolled = window.scrollY > 0;
  }

  constructor(private router: Router,
              private tokenService: TokenService,
              private shareService: ShareService,
              private cartService: CartService,
              private authService: AuthService,
              private activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {

    if (!this.tokenService.isLogined()) {
      this.user = null; // hoặc gán giá trị khác tùy ý
      this.id = null;   // hoặc gán giá trị khác tùy ý
    } else {
      this.authService.profile(this.tokenService.getId()).subscribe(next => {
          this.user = next;
          this.id = this.user.id;
          this.getAllCarts();
          this.shareService.getClickEvent().subscribe(next => {
            this.getAllCarts();
          })
      });
    }
    this.isLogined = this.tokenService.isLogined();
    this.loading();
    this.shareService.getClickEvent().subscribe(next => {
      this.isLogined = this.tokenService.isLogined();
      this.loading();
    });
    this.shareService.sendClickEvent();

    this.router.events
      .pipe(filter((event) => event instanceof NavigationEnd))
      .subscribe((event: NavigationEnd) => {
        this.isHomePageAndSignAndError = this.router.url === '/' || this.router.url === '/sign-in' || this.router.url === '/sign-up' || this.router.url === '/error' ;
      });

    this.router.events
      .pipe(filter((event) => event instanceof NavigationEnd))
      .subscribe((event: NavigationEnd) => {
        this.isSignInPage = this.router.url === '/sign-in';
      });

    this.router.events
      .pipe(filter((event) => event instanceof NavigationEnd))
      .subscribe((event: NavigationEnd) => {
        this.isSignUpPage = this.router.url === '/sign-up';
      });

    this.router.events
      .pipe(filter((event) => event instanceof NavigationEnd))
      .subscribe((event: NavigationEnd) => {
        const urlSegments = this.router.url.split('/');
        this.isLessonPage = urlSegments[1] === 'course' && urlSegments[3] === 'lesson';
      });

    this.router.events
      .pipe(filter((event) => event instanceof NavigationEnd))
      .subscribe((event: NavigationEnd) => {
        const urlSegments = this.router.url.split('/');
        this.isCourseDetailPage = urlSegments.length === 3 && urlSegments[1] === 'course';
        console.log('sdads' + this.isCourseDetailPage)
      });

  }

  logOut() {
    this.tokenService.signOut();
    this.role = 'none';
    this.router.navigateByUrl('/');
    this.shareService.sendClickEvent();
    this.isLogined = false;
  }

  loading(): void {
    if (this.tokenService.getToken()) {
      this.role = this.tokenService.getRole();
      this.name = this.tokenService.getName();
      this.tokenService.setName(this.name);
      console.log(this.role);
    }
    this.isLogined = this.name != null;
    this.tokenService.getName();
  }

  search() {
    this.shareService.changeSearch(this.nameSearch);
    this.router.navigate(['/course'], {queryParams: {nameSearch: this.nameSearch}});
  }


  getAllCarts() {
    this.cartService.getCartByUser(this.id).subscribe(next => {
        this.totalCart = next.length;
    });
  }

}
