import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'rng-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  public homeText: string = 'shadow-sm d-flex flex-column-reverse flex-lg-row justify-content-center container'

  constructor() { }

  ngOnInit(): void {
  }

}
