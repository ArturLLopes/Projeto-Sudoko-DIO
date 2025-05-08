package model;

import model.Enum.GameStatus;

import java.util.Collection;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static model.Enum.GameStatus.*;

public class Board {


    private final List<List<Space>> spaces;

    public List<List<Space>> getSpaces() {
        return spaces;
    }

    public Board(List<List<Space>> spaces) {
        this.spaces = spaces;
    }

    public GameStatus getStatus(){
        if (spaces.stream().flatMap(Collection::stream).noneMatch(s -> !s.isFixed() && nonNull(s.getActual()))){
            //pegamos o spaces usamos o flatMap para ter acesso aos valores da lista mais interna(pegando esses valores)verificamos que nao tem ocorrencia na lista(se ela nao é isFixed e nao for null(algo ja foi preenchido) entao retorna
            return NON_STARTED;
        }
        return spaces.stream().flatMap(Collection::stream).anyMatch(s-> isNull(s.getActual())) ? INCOMPLETE : COMPLETE;
        //verifica que tem alguma ocorrencia na lista, se tiver é incomplete se sim é complete
    }
    public boolean hasErros(){
        if(getStatus() == NON_STARTED){
            return false;
        }
        return spaces.stream().flatMap(Collection::stream)
                .anyMatch(s -> nonNull(s.getActual()) && !s.getActual().equals(s.getExpected()));
    }

    public boolean chargeValue(final int col, final  int row, final  Integer value){
        //list externa = col list interna = row

        var space = spaces.get(col).get(row);
        if (space.isFixed()){
            return false;
        }
        space.setActual(value);
        return true;
    }
    public boolean clearValue(final int col, final int row){
        var space = spaces.get(col).get(row);
        if (space.isFixed()){
            return false;
        }
        space.clearSpace();
        return true;
    }
    public void reset(){
        spaces.forEach(c -> c.forEach(Space::clearSpace));
    }

    public boolean gameIsFinished(){
        return !hasErros() && getStatus().equals(COMPLETE);
                                    //  == COMPLETE
    }


}

