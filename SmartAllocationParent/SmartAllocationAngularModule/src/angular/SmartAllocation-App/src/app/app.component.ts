import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {ng
  title = 'app';
  public showAbout:boolean = true;

  public onRouterOutletActivate(event):void {
    this.showAbout = false;
  }
}
