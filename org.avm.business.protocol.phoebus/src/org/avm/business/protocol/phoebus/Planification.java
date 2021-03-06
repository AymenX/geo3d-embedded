/* @flavorc
 *
 * Planification.java
 * 
 * This file was automatically generated by flavorc
 * from the source file:
 *     'phoebus.fl'
 *
 * For information on flavorc, visit the Flavor Web site at:
 *     http://www.ee.columbia.edu/flavor
 *
 * -- Do not edit by hand --
 *
 */

package org.avm.business.protocol.phoebus;
import java.io.IOException;
import java.io.InputStream;

import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IUnmarshallingContext;

import flavor.Bitstream;
import flavor.IBitstream;
import flavor.MapResult;

public class Planification extends Message {
    int _planification;
    int _checksum;
    int _service;
    int _nbcrs;
    Course _courses[] = new Course[256];
    int _nbpnt;
    Arret _points[] = new Arret[1];
    
	public static final int MESSAGE_TYPE = 15;

    public static final String MESSAGE_NAME = "planification";

   public Planification() {
		super();
		_entete._type = MESSAGE_TYPE;
		_entete._champs._date = 1;
	}

	public Planification(int planification, int checksum, int service, Course[] courses, Arret[] points) {
		super();
		_planification = planification;
		_checksum = checksum;
		_service = service;
		_nbcrs = courses.length;
		_courses = courses;
		_nbpnt = points.length;
		_points = points;
	}

	public int getPlanification() {
		return _planification;
	}

	public void setPlanification(int planification) {
		_planification = planification;
	}

	public int getChecksum() {
		return _checksum;
	}

	public void setChecksum(int checksum) {
		_checksum = checksum;
	}

	public int getService() {
		return _service;
	}

	public void setService(int service) {
		_service = service;
	}

	public Course[] getCourses() {
		Course[] courses = new Course[_nbcrs];
		for (int i = 0; i < courses.length; i++) {
			courses[i] = _courses[i];
		}
		return courses;
	}

	public void setCourses(Course[] courses) {
		for (int i = 0; i < courses.length; i++) {
			_courses[i] = courses[i];
		}
		_nbcrs = courses.length;
	}

	public Arret[] getPoints() {
		Arret[] points = new Arret[_nbpnt];
		for (int i = 0; i < points.length; i++) {
			points[i] = _points[i];
		}
		return points;
	}

	public void setPoints(Arret[] points) {		 
	     _points = new Arret[points.length]; 
	     _nbpnt = _points.length;
		for (int i = 0; i < points.length; i++) {
			_points[i] = points[i];
		}
		_nbpnt = _points.length;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(MESSAGE_NAME + " [" + super.toString());
		sb.append(" [planification " + _planification + " checksum "
				+ _checksum + " service " + _service + " nbcrs " + _nbcrs);
		for (int i = 0; i < _nbcrs; i++) {
			sb.append(" " + _courses[i].toString() + " ");
		}
		sb.append("nbpnt " + _nbpnt);
		for (int i = 0; i < _nbpnt; i++) {
			sb.append(" " + _points[i].toString() + " ");
		}
		sb.append("]");
		return sb.toString();
	}
	
    public static class DefaultMessageFactory extends MessageFactory {

	protected Message unmarshal(InputStream in) throws Exception {
	    IBindingFactory factory = BindingDirectory
		    .getFactory(Planification.class);
	    IUnmarshallingContext context = factory
		    .createUnmarshallingContext();
	    return (Message) context.unmarshalDocument(in, null);

	}

	protected Message get(InputStream in) throws Exception {
	    Bitstream bs = new Bitstream(in);
	    Message message = new Planification();
	    message.get(bs);
	    bs.close();
	    in.reset();
	    return message;
	}

    }

    static {
	MessageFactory.factories.put(new Integer(MESSAGE_TYPE),
		new DefaultMessageFactory());
    }
	

    public int get(IBitstream _F_bs) throws IOException {
        int _F_ret = 0;
        MapResult _F_mr;
        int _F_dim0, _F_dim0_end;
        _F_ret += super.get(_F_bs);
        _planification = _F_bs.sgetbits(32);
        _checksum = _F_bs.getbits(16);
        _service = _F_bs.getbits(32);
        _nbcrs = _F_bs.getbits(8);
        _F_dim0_end = _nbcrs;
        for (_F_dim0 = 0; _F_dim0 < _F_dim0_end; _F_dim0++) {
            _courses[_F_dim0] = new Course();
            _F_ret += _courses[_F_dim0].get(_F_bs);
        }
        _nbpnt = _F_bs.getbits(16);
        _points = new Arret[_nbpnt]; 
        _F_dim0_end = _nbpnt;
        for (_F_dim0 = 0; _F_dim0 < _F_dim0_end; _F_dim0++) {
            _points[_F_dim0] = new Arret();
            _F_ret += _points[_F_dim0].get(_F_bs);
        }
        return _F_ret;
    }

    public int put(IBitstream _F_bs) throws IOException {
        int _F_ret = 0;
        MapResult _F_mr;
        int _F_dim0, _F_dim0_end;
        _F_ret += super.put(_F_bs);
        _F_bs.putbits(_planification, 32);
        _F_bs.putbits(_checksum, 16);
        _F_bs.putbits(_service, 32);
        _F_bs.putbits(_nbcrs, 8);
        _F_dim0_end = _nbcrs;
        for (_F_dim0 = 0; _F_dim0 < _F_dim0_end; _F_dim0++) {
            _F_ret += _courses[_F_dim0].put(_F_bs);
        }
        _F_bs.putbits(_nbpnt, 16);
        _F_dim0_end = _nbpnt;
        for (_F_dim0 = 0; _F_dim0 < _F_dim0_end; _F_dim0++) {
            _F_ret += _points[_F_dim0].put(_F_bs);
        }
        return _F_ret;
    }
}
