import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NameCardComponent } from './name-card/name-card.component';
import { ProgressBarComponent } from './progress-bar/progress-bar.component';
import { RaitingBarComponent } from './raiting-bar/raiting-bar.component';
import { CountdownTimerComponent } from './countdown-timer/countdown-timer.component';

@NgModule({
  declarations: [
    AppComponent,
    NameCardComponent,
    ProgressBarComponent,
    RaitingBarComponent,
    CountdownTimerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
