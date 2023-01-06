import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'rng-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  public styles: string[] = [
    "p-3",
    "shadow-sm d-flex flex-column-reverse",
    "mt-3",
    "text-center p-4",
    "text-center pt-2",
    "p-4 mt-5",
    "img-fluid mt-3"
  ]

  constructor() { }

  ngOnInit(): void {
  }

}
